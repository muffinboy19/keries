package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.keries.R

class More : Fragment() {
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

    }


    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Add to back stack so you can navigate back
            .commit()
    }


}