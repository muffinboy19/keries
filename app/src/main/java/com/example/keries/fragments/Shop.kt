package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.productAdapter
import com.example.keries.dataClass.productDataClass
import com.google.firebase.firestore.FirebaseFirestore

class Shop : Fragment() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productadapter: productAdapter
    private val db = FirebaseFirestore.getInstance()
    private  var productList : MutableList<productDataClass> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRecyclerView = view.findViewById(R.id.productreyclerview)
        productadapter = productAdapter(productList)
        productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productRecyclerView.adapter = productadapter
        fetchFirestoreData()
    }

    private fun fetchFirestoreData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Merch")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val type = document.getString("type") ?: ""
                    val desccription = document.getString("desc") ?: ""
                    val prize = document.getString("cost") ?: ""
                    val url = document.getString("url") ?: ""
                    val item = productDataClass(name,type,desccription,prize,url)
                    productList.add(item)
                }
                productadapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_SHORT).show()

            }
    }

}