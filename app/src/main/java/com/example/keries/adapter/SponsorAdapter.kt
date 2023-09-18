package com.example.keries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keries.R
import com.example.keries.dataClass.sponserDataClass

class SponsorAdapter(private val items: List<sponserDataClass>):
    RecyclerView.Adapter<SponsorAdapter.SponserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sponserlayout, parent, false)
        return SponserViewHolder(view)
    }

    override fun onBindViewHolder(holder: SponserViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class SponserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val sponserImage = itemView.findViewById<ImageView>(R.id.sponserImageView)
        private val sponserNames = itemView.findViewById<TextView>(R.id.sponserName)
        private val sponserDesginations = itemView.findViewById<TextView>(R.id.sponserDesgination)
        fun bind(sponserDataClass: sponserDataClass) {
            sponserNames.text = sponserDataClass.sponserNames
            sponserDesginations.text = sponserDataClass.sponerDesgination
            Glide.with(itemView.context).load(sponserDataClass.sponserImageUrl).into(sponserImage)
        }
    }
}

