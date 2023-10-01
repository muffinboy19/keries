package com.example.keries.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.dataClass.Event_DataClass
import com.example.keries.fragments.Events


class ShowEventAdapter(private val showevents: List<Event_DataClass>, private val itemClickListener: Events) :
    RecyclerView.Adapter<ShowEventAdapter.ShowEventViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(item: Event_DataClass)
    }
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
//    fun setOnItemClickListener(listener: (Event_DataClass) -> Unit) {
//        itemClickListener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowEventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.showeventlayout, parent, false)
        return ShowEventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowEventViewHolder, position: Int) {
        val sees = showevents[position]
        holder.bind(sees)

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(sees)
         }
    }

    override fun getItemCount(): Int {
        return showevents.size
    }
}
