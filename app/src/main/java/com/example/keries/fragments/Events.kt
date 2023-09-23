package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.ShowEventAdapter
import com.example.keries.dataClass.Event_DataClass
import com.google.firebase.firestore.FirebaseFirestore

class Events : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var showEventAdapter: ShowEventAdapter
    private lateinit var nimritiRv: RecyclerView
    private lateinit var rangtaringiniRV: RecyclerView
    private lateinit var sarasvaRV: RecyclerView
    private lateinit var virtuosiRV: RecyclerView
    private lateinit var geneticxRV: RecyclerView
    private lateinit var amsRV: RecyclerView
    private lateinit var gamingRv: RecyclerView
    private lateinit var InformalRv: RecyclerView
    private lateinit var MainStageRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerViews and adapters
        nimritiRv = view.findViewById(R.id.nimritiRV)
        rangtaringiniRV = view.findViewById(R.id.rangtaringiniRV)
        sarasvaRV = view.findViewById(R.id.sarasvaRV)
        virtuosiRV = view.findViewById(R.id.virtuosiRV)
        geneticxRV = view.findViewById(R.id.geneticxRV)
        gamingRv = view.findViewById(R.id.gamingRv)
        InformalRv = view.findViewById(R.id.InformalRv)
        MainStageRV = view.findViewById(R.id.MainStageRV)
        amsRV = view.findViewById(R.id.amsRV)

        // Initialize and populate RecyclerViews with event data
        val ij = mutableListOf<Event_DataClass>()
        showEventAdapter = ShowEventAdapter(ij,this)
        fetchFromFireStoreEvents("AMS", amsRV)
        fetchFromFireStoreEvents("Dance", geneticxRV)
        fetchFromFireStoreEvents("Dramatics", rangtaringiniRV)
        fetchFromFireStoreEvents("Fine Arts", nimritiRv)
        fetchFromFireStoreEvents("Literature", sarasvaRV)
        fetchFromFireStoreEvents("Music", virtuosiRV)
        fetchFromFireStoreEvents("Gaming", gamingRv)
        fetchFromFireStoreEvents("Informal", InformalRv)
        fetchFromFireStoreEvents("Main Stage", MainStageRV)


    }

    fun onItemClick(item: Event_DataClass){
        val nextFragment = eventdetails()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,nextFragment)
        transaction.commit()
    }

    private fun fetchFromFireStoreEvents(eventType: String, recyclerView: RecyclerView) {
        // Fetch event data from Firestore for the specified event type
        db.collection(eventType)
            .get()
            .addOnSuccessListener {
                val showeventlist = mutableListOf<Event_DataClass>()
                for (document in it) {
                    val date = document.getString("name") ?: ""
                    val details = document.getString("url") ?: ""
                    val form = document.getString("url") ?: ""
                    val name = document.getString("url") ?: ""
                    val no = document.getLong("no") ?: 0
                    val time = document.getString("url") ?: ""
                    val url = document.getString("url") ?: ""
                    val venue = document.getString("url") ?: ""
                    showeventlist.add(
                        Event_DataClass(date, details, form, name, no, time, url, venue)
                    )
                }

                // Set up the RecyclerView adapter with the retrieved event data
                showEventAdapter = ShowEventAdapter(showeventlist, this)
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = showEventAdapter
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
    }
}
