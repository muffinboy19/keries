package com.example.keries.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keries.R
import com.example.keries.dataClass.Event_DataClass
import com.example.keries.dataClass.FeaturedEventes
import com.example.keries.fragments.Events

class featuredEventsAdapter(private val items: List<FeaturedEventes>) :
    RecyclerView.Adapter<featuredEventsAdapter.FeaturedEventesViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(item:Event_DataClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedEventesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.featuredeventlayout, parent, false)
        return FeaturedEventesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedEventesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

//        holder.itemView.setOnClickListener{
//            itemClickListener.onItemClick(item)
//        }
    }

    override fun getItemCount(): Int = items.size

    inner class FeaturedEventesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageOfEvent  = itemView.findViewById<ImageView>(R.id.featuredEventImageView)
        private val titleOfEvent = itemView.findViewById<TextView>(R.id.featuredEventTitle)
        fun bind(featuredEventes: FeaturedEventes) {
            titleOfEvent.text = featuredEventes.EventTitle
            Glide.with(itemView.context).load(featuredEventes.EventImageUrl).into(imageOfEvent)
        }
    }
}
