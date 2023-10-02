package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.NotificationAdapter
import com.example.keries.dataClass.NotificationModel
import com.google.firebase.firestore.FirebaseFirestore

class notification : Fragment() {

//    private lateinit var viewModel: NotificationViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private var NotificatonList : MutableList<NotificationModel> = mutableListOf()
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_notification, container, false)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "Notification"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

//        val back = root.findViewById<ImageView>(R.id.boso)
        logoTool.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        // Initialize ViewModel
//        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.notificationRecyclerView)
        notificationAdapter = NotificationAdapter(NotificatonList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = notificationAdapter
//        val adapter = NotificationAdapter(viewModel.notifications)
//        recyclerView.adapter = adapter
        fetchFirestoreData()
    }

    private fun fetchFirestoreData() {
        val db = FirebaseFirestore.getInstance()
            db.collection("Notification")
                .get()
                .addOnSuccessListener { documents ->
                    for(document in documents){
                        val info = document.getString("info")?:""
//                        val image = document.getString("c")?:""
//                        val time = document.getString("time")?:""
                        val item = NotificationModel(info)
                        NotificatonList.add(item)
                    }
                    notificationAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
    }
}