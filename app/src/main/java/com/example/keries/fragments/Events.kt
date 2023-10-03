package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
    private  var ij : MutableList<Event_DataClass> = mutableListOf()
    private lateinit var countdownTextView: TextView
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView

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
        amsRV = view.findViewById(R.id.amsRV)

        // Initialize and populate RecyclerViews with event data

        showEventAdapter = ShowEventAdapter(ij,this)
        fetchFromFireStoreEvents("AMS", amsRV)
        fetchFromFireStoreEvents("Dance", geneticxRV)
        fetchFromFireStoreEvents("Dramatics", rangtaringiniRV)
        fetchFromFireStoreEvents("Fine Arts", nimritiRv)
        fetchFromFireStoreEvents("Literature", sarasvaRV)
        fetchFromFireStoreEvents("Music", virtuosiRV)
        fetchFromFireStoreEvents("Gaming", gamingRv)
        fetchFromFireStoreEvents("Informal", InformalRv)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "EVENTS"
        notifyTool.setVisibility(View.GONE)
        logoTool.setVisibility(View.GONE)


    }

    fun onItemClick(item: Event_DataClass){
        val bundle=Bundle()
        bundle.putString("date" , item.date?:"Date")
        bundle.putString("details" , item.details)
        bundle.putString("form" , item.form?:"Form")
        bundle.putString("name" , item.name?:"Name")
        bundle.putLong("no" , item.no?:123)
        bundle.putString("time" , item.time?:"Time")
        bundle.putString("url" , item.url?:"Url")
        bundle.putString("venue" , item.venue?:"Venue")
        val nextFragment = eventdetails()
        nextFragment.arguments=bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,nextFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun fetchFromFireStoreEvents(eventType: String, recyclerView: RecyclerView) {

        ij.clear()
        // Fetch event data from Firestore for the specified event type
        db.collection(eventType)
            .get()
            .addOnSuccessListener {
                val showeventlist = mutableListOf<Event_DataClass>()
                for (document in it) {
                    val date = document.getString("date")?:""
                    val details = document.getString("details")?:""
                    val form = document.getString("form")?:""
                    val name = document.getString("name")?:""
                    val no = document.getLong("no")?:0
                    val time = document.getString("time")?:""
                    val url = document.getString("url")?:""
                    val venue = document.getString("venue")?:""
                    showeventlist.add(
                        Event_DataClass(date, details, form, name, no, time, url, venue)
                    )
                }

                // Set up the RecyclerView adapter with the retrieved event data
                showEventAdapter = ShowEventAdapter(showeventlist, this)
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//                recyclerView.layoutManager = CustomLayoutManager(requireContext())
//                recyclerView.itemAnimator  = CustomItemAnimator()
                recyclerView.adapter = showEventAdapter
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
    }
}
