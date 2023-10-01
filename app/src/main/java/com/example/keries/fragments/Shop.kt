package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.productAdapter
import com.example.keries.dataClass.productDataClass
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.firestore.FirebaseFirestore

class Shop : Fragment() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productadapter: productAdapter
    private val db = FirebaseFirestore.getInstance()
    private  var productList : MutableList<productDataClass> = mutableListOf()
    private lateinit var shimmerFrameLayout:ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_shop, container, false)
        shimmerFrameLayout= root.findViewById(R.id.shimmer)

        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setDuration(3000)
            .setAutoStart(true)
            .build()
        shimmerFrameLayout.setShimmer(shimmer)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        productRecyclerView = view.findViewById(R.id.productreyclerview)
        productadapter = productAdapter(productList,this)

        // Set the layout manager and adapter for the RecyclerView
        productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productRecyclerView.adapter = productadapter

        if(savedInstanceState==null) {
            // Fetch data from Firestore
            fetchFirestoreData()
        }
    }

    fun onItemClick(item:productDataClass){
        val bundle=Bundle()
        bundle.putString("prize" , item.productPrize)
        bundle.putString("name" , item.productNames)
        bundle.putString("type" , item.productTypes)
        bundle.putString("descrip" , item.productDescription)
        bundle.putString("image" , item.productImageUrl)
        bundle.putString("form",item.productForm)
        val nextFragment = Shop2()
        nextFragment.arguments=bundle

        if(!isCurrentFragment(nextFragment)){
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,nextFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun fetchFirestoreData() {

        productList.clear()
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
                    val form = document.getString("form")?:""
                    val item = productDataClass(name, type, desccription, prize, url,form)
                    productList.add(item)
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.isVisible =false
                    productRecyclerView.isVisible = true
                }

                // Notify the adapter that the data set has changed
                productadapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

    private fun isCurrentFragment(nextFragment: Fragment):Boolean {
        val current = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container)
        return current !=null && current::class.java==nextFragment::class.java

    }
}
