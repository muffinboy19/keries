package com.example.keries.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.keries.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class eventdetails : Fragment() {
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.event_layout, container, false)
        val register = root.findViewById<AppCompatButton>(R.id.register)
        val societyName = root.findViewById<TextView>(R.id.textView2)
        val eventName = root.findViewById<TextView>(R.id.textView)
        val eventDescription = root.findViewById<TextView>(R.id.descriptionEventTextView)
        val location = root.findViewById<TextView>(R.id.locationTextView)
        val time = root.findViewById<TextView>(R.id.TimeTextView)
        val image = root.findViewById<ImageView>(R.id.eventImage)

        val date = arguments?.getString("date")
        val details = arguments?.getString("details")
        val formLink = arguments?.getString("form")
        val name = arguments?.getString("name")
        val timee = arguments?.getString("time")
        val url = arguments?.getString("url")
        val venue = arguments?.getString("venue")

        register.setOnClickListener {
            // Check if the formLink is not null or empty
            if (!formLink.isNullOrBlank()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(formLink))
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Error while Loading the link", Toast.LENGTH_SHORT).show()
            }
        }


//        societyName.text =
        eventDescription.text = details
        eventName.text = name
        location.text=venue
        time.text=timee
        time.text=timee
        Picasso.get().load(url).into(image)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "EVENTS"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return root
    }
}