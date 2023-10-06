package com.example.keries.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.keries.R

class FAQ : Fragment() {

    // Store button states (whether they are clicked or not)
    private val buttonStates = mutableMapOf<ImageView, Boolean>()
    // Set the initial drop-down height
    private val dropHeight = 500
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f_a_q2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "FAQs"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.back_svgrepo_com)
        logoTool.setVisibility(View.VISIBLE)

        // Initialize UI elements
        val d1 = view.findViewById<ImageView>(R.id.d1)
        val d2 = view.findViewById<ImageView>(R.id.d2)
        val d3 = view.findViewById<ImageView>(R.id.d3)
        val d4 = view.findViewById<ImageView>(R.id.d4)
        val d5 = view.findViewById<ImageView>(R.id.d5)
        val d6 = view.findViewById<ImageView>(R.id.d6)
        val a1 = view.findViewById<TextView>(R.id.a1)
        val a2 = view.findViewById<TextView>(R.id.a2)
        val a3 = view.findViewById<TextView>(R.id.a3)
        val a4 = view.findViewById<TextView>(R.id.a4)
        val a5 = view.findViewById<TextView>(R.id.a5)
        val a6 = view.findViewById<TextView>(R.id.a6)
        val q1 = view.findViewById<TextView>(R.id.q1)
        val q2 = view.findViewById<TextView>(R.id.q2)
        val q3 = view.findViewById<TextView>(R.id.q3)
        val q4 = view.findViewById<TextView>(R.id.q4)
        val q5 = view.findViewById<TextView>(R.id.q5)
        val q6 = view.findViewById<TextView>(R.id.q6)
        val l1 = view.findViewById<LinearLayout>(R.id.l1)
        val l2 = view.findViewById<LinearLayout>(R.id.l2)
        val l3 = view.findViewById<LinearLayout>(R.id.l3)
        val l4 = view.findViewById<LinearLayout>(R.id.l4)
        val l5 = view.findViewById<LinearLayout>(R.id.l5)
        val l6 = view.findViewById<LinearLayout>(R.id.l6)

        // Initialize the return button
//        val retw = view.findViewById<ImageView>(R.id.boso)
        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Initialize button states as false (not clicked)
        buttonStates[d1] = false
        buttonStates[d2] = false
        buttonStates[d3] = false
        buttonStates[d4] = false
        buttonStates[d5] = false
        buttonStates[d6] = false

        // Set click listeners for each button
        l1.setOnClickListener {
            downFunction(a1, d1,q1, l1)
        }
        l2.setOnClickListener {
            downFunction(a2, d2, q2,l2)
        }
        l3.setOnClickListener {
            downFunction(a3, d3, q3,l3)
        }
        l4.setOnClickListener {
            downFunction(a4, d4, q4,l4)
        }
        l5.setOnClickListener {
            downFunction(a5, d5, q5 ,l5)
        }
        l6.setOnClickListener {
            downFunction(a6, d6, q6, l6)
        }
    }

    // Toggle the visibility of the answer text and rotate the arrow button
    private fun downFunction(a: TextView, d: ImageView, q:TextView, l:LinearLayout) {
        val currentState = buttonStates[d] ?: false
        if (currentState) {
            // If the answer is visible, hide it and rotate the arrow button back to default
            a.visibility = View.GONE
            a.setTextColor(Color.WHITE)
            q.setTextColor(Color.WHITE)
            d.animate().rotation(0F)
            d.setColorFilter(Color.WHITE)
            l.setBackgroundColor(Color.parseColor("#282828"))

        } else {
            // If the answer is hidden, show it and rotate the arrow button 180 degrees
            a.visibility = View.VISIBLE
            d.animate().rotation(180F)
            a.setTextColor(Color.BLACK)
            q.setTextColor(Color.BLACK)
            d.setColorFilter(Color.BLACK)
            l.setBackgroundColor(Color.WHITE)
        }
        // Update the button state
        buttonStates[d] = !currentState
    }
}






//    private fun toggleDropDownAnimation(a: TextView, d: ImageView) {
//        val currentState = buttonStates[d] ?: false
//
//        val translateYAnimator = if (currentState) {
//            ValueAnimator.ofInt(dropHeight, 0)
//        } else {
//            ValueAnimator.ofInt(0, dropHeight)
//        }
//
//        translateYAnimator.addUpdateListener { valueAnimator ->
//            val value = valueAnimator.animatedValue as Int
//            a.translationY = value.toFloat()
//        }
//
//        translateYAnimator.duration = 500
//        val rotateAnimator = ObjectAnimator.ofFloat(d, "rotation", if (currentState) 0f else 180f)
//
//        translateYAnimator.start()
//        rotateAnimator.start()
//
//        // Update the state in the map
//        buttonStates[d] = !currentState
//    }


//    private fun toggleDropDownAnimation(a: TextView, d: ImageView) {
//        val currentState = buttonStates[d] ?: false
//
//        if (currentState) {
//            // Collapse animation
//            a.animate().translationY(0f).setDuration(300).start()
//            d.animate().rotation(0f).setDuration(300).start()
//        } else {
//            // Expand animation
//            a.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//            val targetHeight = a.measuredHeight
//
//            a.translationY = -targetHeight.toFloat()
//            a.animate().translationY(0f).setDuration(300).start()
//            d.animate().rotation(180f).setDuration(300).start()
//        }
//
//        // Update the state in the map
//        buttonStates[d] = !currentState
//    }