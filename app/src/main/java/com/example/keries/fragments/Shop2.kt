package com.example.keries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.keries.R
import com.example.keries.dataClass.productDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class Shop2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_shop2, container, false)
        val merchPrice = rootView.findViewById<TextView>(R.id.textView3)
        val merchImage = rootView.findViewById<ImageView>(R.id.shopImage)
        val merchName = rootView.findViewById<TextView>(R.id.textView1)
        val merchType = rootView.findViewById<TextView>(R.id.textView2)
        val merchDescription = rootView.findViewById<TextView>(R.id.productDescription)
        val boso = rootView.findViewById<ImageView>(R.id.backBut)

        boso.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        val prize = arguments?.getString("prize")
        val name = arguments?.getString("name")
        val type = arguments?.getString("type")
        val descrip = arguments?.getString("descrip")
        val image = arguments?.getString("image")

        merchName.text = name
        merchType.text = type
        merchPrice.text = prize
        merchDescription.text = descrip
        Picasso.get().load(image).into(merchImage)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
