package com.example.keries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.keries.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class eventdetails : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_eventdetails, container, false)

        val societyName = root.findViewById<TextView>(R.id.textView2)
        val eventName = root.findViewById<TextView>(R.id.textView)
        val eventDescription = root.findViewById<TextView>(R.id.descriptionEventTextView)
        val location = root.findViewById<TextView>(R.id.locationTextView)
        val time = root.findViewById<TextView>(R.id.TimeTextView)
        val image = root.findViewById<ImageView>(R.id.eventImage)

        val date = arguments?.getString("date")
        val details = arguments?.getString("details")
        val form = arguments?.getString("form")
        val name = arguments?.getString("name")
        val no = arguments?.getString("no")
        val timee = arguments?.getString("time")
        val url = arguments?.getString("url")
        val venue = arguments?.getString("venue")

        if(details!=null){
            eventDescription.text = details
        }
        if(name!=null){
            eventName.text = name
        }
        if(location!=null){
            location.text=venue
            time.text=timee
        }
        if(time!=null){
            time.text=timee
        }
//        if(url!=null){
//            Picasso.get().load(url).into(image)
//        }
        return root
    }

}