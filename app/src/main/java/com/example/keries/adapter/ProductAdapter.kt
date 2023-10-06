package com.example.keries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keries.R
import com.example.keries.dataClass.productDataClass
import com.example.keries.fragments.Shop

class productAdapter(private val items: List<productDataClass>,
                     private val itemClickListener: Shop
):
    RecyclerView.Adapter<productAdapter.productViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(item:productDataClass)
    }

    // Create ViewHolder by inflating the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shoplayolt, parent, false)
        return productViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(item)
        }
    }

    // Return the number of items in the dataset
    override fun getItemCount(): Int = items.size

    // Inner ViewHolder class to hold references to views
    inner class productViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var productNameTextView = itemView.findViewById<TextView>(R.id.productName)
//        private var productDesctriptionTextView = itemView.findViewById<TextView>(R.id.productDescription)
        private var productTypeTextView = itemView.findViewById<TextView>(R.id.productType)
//        private var productPrizeTextView = itemView.findViewById<TextView>(R.id.produtPrize)
//        private var productImageView = itemView.findViewById<ImageView>(R.id.productImage)

        // Bind data to views
        fun bind(productDataClass: productDataClass) {
            productNameTextView.text = productDataClass.productNames
//            productDesctriptionTextView.text = productDataClass.productDescription
//            productPrizeTextView.text = productDataClass.productPrize
            productTypeTextView.text = productDataClass.productTypes

            // Load image using Glide
//            Glide.with(itemView.context).load(productDataClass.productImageUrl).into(productImageView)
        }
    }
}
