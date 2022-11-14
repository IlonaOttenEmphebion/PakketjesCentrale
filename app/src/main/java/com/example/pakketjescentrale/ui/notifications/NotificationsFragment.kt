package com.example.pakketjescentrale.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pakketjescentrale.MainActivity
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.ui.newparcel.NewParcelActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private val packages = arrayListOf<Parcel>()
    private val notificationsAdapter = NotivicationsAdapter(packages)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_otherParcels)
        val addParcelButton: FloatingActionButton = root.findViewById(R.id.addParcelButton)
        val parcelRepository: ParcelRepository = ParcelRepository()
/*        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        addParcelButton.setOnClickListener { onParcelBtnClick() }

        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = notificationsAdapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse: Parcel =
                    packages.get(viewHolder.adapterPosition)

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                packages.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                notificationsAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                Snackbar.make(
                    recyclerView,
                    "Deleted " + deletedCourse.parcelID.toString(),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            packages.add(position, deletedCourse)

                            // below line is to notify item is
                            // added to our adapter class.
                            notificationsAdapter.notifyItemInserted(position)
                        }).show()
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView)

        parcelRepository.getMyParcels().enqueue(object :
            Callback<Array<Parcel>> {
            override fun onFailure(call: Call<Array<Parcel>>, t: Throwable) {
                print("getMyParcels failed")
            }

            override fun onResponse(call: Call<Array<Parcel>>, response: Response<Array<Parcel>>) {
                if (response.isSuccessful) {
                    packages.addAll(response.body()!!);

                    notificationsAdapter.notifyDataSetChanged()
                }
            }
        })

        return root
    }

    private fun onParcelBtnClick() {
        val intent = Intent(this.context, NewParcelActivity::class.java)
        startActivityForResult(
            intent,
            MainActivity.ADD_PARCEL_REQUEST_CODE
        )
    }
}