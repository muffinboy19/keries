package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.keries.R

class More : Fragment() {
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val FAQ = view.findViewById<LinearLayout>(R.id.FAQ)
        FAQ.setOnClickListener {
            loadFragment(FAQ())
        }
        val TEAM = view.findViewById<LinearLayout>(R.id.teamlayout)
        TEAM.setOnClickListener {
            loadFragment(Team())
        }
        val SPONSER = view.findViewById<LinearLayout>(R.id.SPONS)
        SPONSER.setOnClickListener {
            loadFragment(sponser())
        }
        val ABOUT = view.findViewById<LinearLayout>(R.id.AboutUsss)
        ABOUT.setOnClickListener {
            loadFragment(about())
        }
        val dev = view.findViewById<LinearLayout>(R.id.Devs)
        dev.setOnClickListener {
            loadFragment(developers())
        }

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "MORE"
        notifyTool.setVisibility(View.GONE)
        logoTool.setVisibility(View.GONE)
    }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Add to back stack so you can navigate back
            .commit()
    }


}