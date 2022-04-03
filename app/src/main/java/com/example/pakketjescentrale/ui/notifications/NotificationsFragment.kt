package com.example.pakketjescentrale.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.model.Parcel
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
        val parcelRepository: ParcelRepository = ParcelRepository()
/*        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = notificationsAdapter

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
}