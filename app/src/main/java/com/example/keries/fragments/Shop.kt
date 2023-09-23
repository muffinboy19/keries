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
import com.example.keries.Shop2
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        productRecyclerView = view.findViewById(R.id.productreyclerview)
        productadapter = productAdapter(productList,this)

        // Set the layout manager and adapter for the RecyclerView
        productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productRecyclerView.adapter = productadapter

        // Fetch data from Firestore
        fetchFirestoreData()
    }

    fun onItemClick(item:productDataClass){
        val nextFragment = Shop2()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,nextFragment)
        transaction.addToBackStack(null)
        transaction.commit()
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
                    val item = productDataClass(name, type, desccription, prize, url)
                    productList.add(item)
                }

                // Notify the adapter that the data set has changed
                productadapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}
