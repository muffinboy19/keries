package com.example.keries.fragments


import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.keries.adapter.featuredEventsAdapter
import com.example.keries.dataClass.FeaturedEventes
import com.google.firebase.firestore.FirebaseFirestore
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Home : Fragment() {
    private lateinit var mainstageEventRecyclerView: RecyclerView
    private lateinit var mainStageEventAdapter : featuredEventsAdapter
    private  var aox  : MutableList<FeaturedEventes>  = mutableListOf()
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

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainstageEventRecyclerView = view.findViewById(R.id.FeaturedEventRecylerView)
        mainStageEventAdapter = featuredEventsAdapter(aox,this)


        mainstageEventRecyclerView.layoutManager =
            CarouselLayoutManager(true,true, 0.5F,true,true,true, LinearLayoutManager.HORIZONTAL)
        mainstageEventRecyclerView.adapter = mainStageEventAdapter

        countdownTextView = view.findViewById(R.id.countdownTextView)
        fetchSystemDateTime()
        fetchFromFireStoreEvents("Main Stage",mainstageEventRecyclerView)

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

    fun onItemClick(item: FeaturedEventes){
        val bundle=Bundle()
        bundle.putString("date" , item.date?:"Date")
        bundle.putString("details" , item.details?:"Details")
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
        aox.clear()

        // Fetch event data from Firestore for the specified event type
        db.collection(eventType)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val showeventlist = mutableListOf<FeaturedEventes>()
                for (document in querySnapshot) {
                    val date = document.getString("date") ?: ""
                    val details = document.getString("details") ?: ""
                    val form = document.getString("form") ?: ""
                    val name = document.getString("name") ?: ""
                    val no = document.getLong("no") ?: 0
                    val time = document.getString("time") ?: ""
                    val url = document.getString("url") ?: ""
                    val venue = document.getString("venue") ?: ""
                    showeventlist.add(
                        FeaturedEventes(date, details, form, name, no, time, url, venue)
                    )
                }


                // Add the data to your existing adapter and notify it to update the RecyclerView
                aox.addAll(showeventlist)
                mainStageEventAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
    }
}
