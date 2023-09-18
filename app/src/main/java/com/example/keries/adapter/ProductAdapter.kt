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


class productAdapter(private val items: List<productDataClass>):
    RecyclerView.Adapter<productAdapter.productViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shoplayolt, parent, false)
        return productViewHolder(view)
    }

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class productViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var productNameTextView = itemView.findViewById<TextView>(R.id.productName)
        private var productDesctriptionTextView = itemView.findViewById<TextView>(R.id.productDescription)
        private var productTypeTextView = itemView.findViewById<TextView>(R.id.productType)
        private var productPrizeTextView = itemView.findViewById<TextView>(R.id.produtPrize)
        private var productImageView = itemView.findViewById<ImageView>(R.id.productImage)

        fun bind(productDataClass: productDataClass) {
            productNameTextView.text = productDataClass.productNames
            productDesctriptionTextView.text = productDataClass.productDescription
            productPrizeTextView.text = productDataClass.productPrize
            productTypeTextView.text = productDataClass.productTypes
            Glide.with(itemView.context).load(productDataClass.productImageUrl).into(productImageView)
        }
    }
}

