package com.example.pakketjescentrale.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pakketjescentrale.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val packages = arrayListOf<ParcelText>()
    private val dashboardAdapter = DashboardAdapter(packages)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root

        packages.add(ParcelText("Do Homework"))
        packages.add(ParcelText("Answer Questions"))
    }

//    private fun initViews() {
//        // Initialize the recycler view with a linear layout manager, adapter
//        rv_myParcels.layoutManager = LinearLayoutManager(this@DashboardFragment, RecyclerView.VERTICAL, false)
//        rv_myParcels.adapter = dashboardAdapter
//        rv_myParcels.addItemDecoration(DividerItemDecoration(this@DashboardFragment, DividerItemDecoration.VERTICAL))
//    }
}