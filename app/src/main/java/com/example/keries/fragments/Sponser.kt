package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.SponsorAdapter
import com.example.keries.dataClass.sponserDataClass
import com.google.firebase.firestore.FirebaseFirestore


class sponser : Fragment() {


    private lateinit var sponsorRecyclerView: RecyclerView
    private lateinit var sponseradapter: SponsorAdapter
//    private val db = FirebaseFirestore.getInstance()
    private  var SponserList : MutableList<sponserDataClass> = mutableListOf()
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sponser, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sponsorRecyclerView = view.findViewById(R.id.sponserRecylerView)

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "Sponsers"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        sponseradapter = SponsorAdapter(SponserList)
        sponsorRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        sponsorRecyclerView.adapter = sponseradapter
        fetchFirestoreData()


    }

    private fun fetchFirestoreData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("sponsors")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val url = document.getString("url") ?: ""
                    val desgination = document.getString("title")?:""
                    val item = sponserDataClass(name,desgination,url)
                    SponserList.add(item)
                }
                sponseradapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()

            }
    }


}