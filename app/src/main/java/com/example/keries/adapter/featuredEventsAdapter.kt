package com.example.keries.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.keries.R
import com.example.keries.dataClass.FeaturedEventes
import com.example.keries.fragments.Home

class featuredEventsAdapter(private val items: List<FeaturedEventes>,private val itemClickListener: Home) :
    RecyclerView.Adapter<featuredEventsAdapter.FeaturedEventesViewHolder>() {

    interface boxo{
        fun OnItemClick(item:FeaturedEventes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedEventesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.featuredeventlayout, parent, false)
        return FeaturedEventesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedEventesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class FeaturedEventesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageOfEvent = itemView.findViewById<ImageView>(R.id.featuredEventImageView)

        fun bind(featuredEventes: FeaturedEventes) {
            Glide.with(itemView.context)
                .load(featuredEventes.url)
                .placeholder(R.drawable.ic_launcher_background) // Add a placeholder image
                .error(R.drawable.location_pin_svgrepo_com) // Add an error image
                .into(imageOfEvent)
        }
    }
}
