package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.NotificationAdapter
import com.example.keries.dataClass.NotificationViewModel

class notification : Fragment() {

    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_notification, container, false)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]

        // Initialize RecyclerView
        val recyclerView = root.findViewById<RecyclerView>(R.id.notificationRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NotificationAdapter(viewModel.notifications)
        recyclerView.adapter = adapter

        return root
    }
}