package com.example.keries.fragments


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.ShowEventAdapter
import com.example.keries.adapter.featuredEventsAdapter
import com.example.keries.dataClass.Event_DataClass
import com.example.keries.dataClass.FeaturedEventes
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Home : Fragment() {


    private lateinit var FeaturedEventRecylerView: RecyclerView
    private lateinit var featuredEventsAdapter : featuredEventsAdapter
    private  var FeaturedEventsList  : MutableList<FeaturedEventes> = mutableListOf()
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    private val db = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FeaturedEventRecylerView = view.findViewById(R.id.FeaturedEventRecylerView)
        featuredEventsAdapter = featuredEventsAdapter(FeaturedEventsList)
        FeaturedEventRecylerView.layoutManager = LinearLayoutManager(requireContext())
        FeaturedEventRecylerView.adapter = featuredEventsAdapter
        countdownTextView = view.findViewById(R.id.countdownTextView)
        fetchSystemDateTime()

//        featuredEventsAdapter = featuredEventsAdapter(FeaturedEventsList)
//        fetchFromFireStoreEvents("Main Stage", FeaturedEventRecylerView)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "EFFERVESCENCE'23"
        notifyTool.setVisibility(View.VISIBLE)
        logoTool.setVisibility(View.VISIBLE)
        logoTool.setImageResource(R.drawable.bluevg)

        notifyTool.setOnClickListener {
            loadFragment(notification())
        }
    }

//    private fun fetchFirestoreData() {
//        db.collection("items")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    val title = document.getString("title") ?: ""
//                    val imageUrl = document.getString("imageUrl") ?: ""
//                    val item = FeaturedEventes(title, imageUrl)
//                    FeaturedEventsList.add(item)
//                }
//                featuredEventsAdapter.notifyDataSetChanged()
//            }
//            .addOnFailureListener { exception ->
//
//            }
//    }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Add to back stack so you can navigate back
            .commit()
    }

    private fun fetchSystemDateTime() {
        val currentTimeMillis = System.currentTimeMillis()
        val targetDateString = "2023-10-23T23:59:59" // Replace with your target date
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val targetDate = sdf.parse(targetDateString)
        val timeDifferenceMillis = targetDate.time - currentTimeMillis

        startCountdown(timeDifferenceMillis)
    }

    private fun startCountdown(timeInMillis: Long) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                val countdownText = String.format(
                    "%02d:%02d:%02d:%02d",
                    days, hours, minutes, seconds
                )
                countdownTextView.text = countdownText
            }

            override fun onFinish() {
                countdownTextView.text = "Countdown finished"
            }
        }.start()
    }
    private fun fetchFromFireStoreEvents(eventType: String, recyclerView: RecyclerView) {

        FeaturedEventsList.clear()
        // Fetch event data from Firestore for the specified event type
        db.collection(eventType)
            .get()
            .addOnSuccessListener {
                val showeventlist = mutableListOf<FeaturedEventes>()
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
                        FeaturedEventes(date, details, form, name, no, time, url, venue)
                    )
                }

                // Set up the RecyclerView adapter with the retrieved event data
                featuredEventsAdapter = featuredEventsAdapter(FeaturedEventsList)
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = featuredEventsAdapter
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
    }
}
