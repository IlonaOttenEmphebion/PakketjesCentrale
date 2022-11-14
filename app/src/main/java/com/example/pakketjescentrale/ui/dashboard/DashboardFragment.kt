package com.example.pakketjescentrale.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.model.ParcelLocation
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val packages = arrayListOf<Parcel>()
    private val dashboardAdapter = DashboardAdapter(packages)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_myParcels)
        val parcelRepository: ParcelRepository = ParcelRepository()

        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = dashboardAdapter



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
                dashboardAdapter.notifyItemRemoved(viewHolder.adapterPosition)

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
                            dashboardAdapter.notifyItemInserted(position)
                        }).show()
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView)



        parcelRepository.getParcelsReceived().enqueue(object :
            Callback<Array<Parcel>> {
            override fun onFailure(call: Call<Array<Parcel>>, t: Throwable) {
                print("getParcelsReceived failed")
            }

            override fun onResponse(call: Call<Array<Parcel>>, response: Response<Array<Parcel>>) {
                if (response.isSuccessful) {
                    packages.addAll(response.body()!!);

                    dashboardAdapter.notifyDataSetChanged()
                }
            }
        })

        return root
    }
}