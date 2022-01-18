package com.example.pakketjescentrale.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pakketjescentrale.MainActivity
import com.example.pakketjescentrale.R
import com.example.pakketjescentrale.data.parceldatabase.ParcelDataBaseApi
import com.example.pakketjescentrale.data.parceldatabase.ParcelRepository
import com.example.pakketjescentrale.model.AuthenticationResponse
import com.example.pakketjescentrale.model.Parcel
import com.example.pakketjescentrale.model.ParcelLocation
import com.example.pakketjescentrale.model.ParcelResult
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val packages = arrayListOf<Parcel>()
    private val dashboardAdapter = DashboardAdapter(packages)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        packages.add(ParcelText("Do Homework"))
        packages.add(ParcelText("Answer Questions"))

        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_myParcels)
        val parcelRepository: ParcelRepository = ParcelRepository()

        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = dashboardAdapter

        parcelRepository.getParcelsReceived().enqueue(object :
            Callback<Array<Parcel>> {
            override fun onFailure(call: Call<Array<Parcel>>, t: Throwable) {
                print("ugygv")
            }

            override fun onResponse(call: Call<Array<Parcel>>, response: Response<Array<Parcel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { packages.addAll(it) }
                    dashboardAdapter.notifyDataSetChanged()
                }
            }
        })

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}