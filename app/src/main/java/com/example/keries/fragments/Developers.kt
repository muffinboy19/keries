package com.example.keries.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.keries.R

class developers : Fragment() {
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    private lateinit var lottieDev: LottieAnimationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_developers, container, false)

        lottieDev = requireActivity().findViewById(R.id.lottieAnimationView)

        lottieDev.visibility = View.VISIBLE
            lottieDev.playAnimation()

//         Delay for a specified duration (e.g., 3000 milliseconds or 3 seconds)
            val delayMillis = 3000L
            Handler(Looper.getMainLooper()).postDelayed({
                // Stop the animation after the delay
                lottieDev.cancelAnimation()
                lottieDev.visibility = View.GONE
            }, delayMillis)
        
        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "Developers"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)
        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return root
    }
}