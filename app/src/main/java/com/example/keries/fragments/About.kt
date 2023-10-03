package com.example.keries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.keries.R

class about : Fragment() {
    private lateinit var text : TextView
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "About us"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        text = view.findViewById(R.id.aboutText)
        text.text = "Nationally acknowledged for its array of unforgettable evenings, EFFERVESCENCE is the once in a year cultural epitome of IIITA illuminating the essence and ethos of IIITA witnessing a footfall of 21k+ per year.Effervesence has made a name for itself for the phenomenally successful online versions of the fest due to generous views and comments during the talk shows, comedy gigs and body-grooving musical performances. Each event reaches a different ke audience segment and provides a fun, entertaining and engaging way to wow the audience."

    }
}