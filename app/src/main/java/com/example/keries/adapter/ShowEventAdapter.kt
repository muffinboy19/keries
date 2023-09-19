package com.example.keries.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.keries.R
import com.example.keries.dataClass.Event_DataClass


class ShowEventAdapter(private val showevents: List<Event_DataClass>) :
    RecyclerView.Adapter<ShowEventAdapter.ShowEventViewHolder>() {

    inner class ShowEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.eventImageShow)


        fun bind(se: Event_DataClass) {
            Glide.with(itemView.context)
                .load(se.url)
                .placeholder(R.drawable.ic_launcher_background) // Add a placeholder image
                .error(R.drawable.location_pin_svgrepo_com) // Add an error image
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowEventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.showeventlayout, parent, false)
        return ShowEventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowEventViewHolder, position: Int) {
        val sees = showevents[position]
        holder.bind(sees)
    }

    override fun getItemCount(): Int {
        return showevents.size
    }
}
