package com.example.keries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class TeamAdapter(private val teamMembers: List<TeamMember>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val wingTextView: TextView = itemView.findViewById(R.id.desginations)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)


        fun bind(teamMember: TeamMember) {
            nameTextView.text = teamMember.name
            if(teamMember.no>42){
                wingTextView.text = "EXECUTIVE"
            }
            else{
                wingTextView.text = "HEAD"
            }
//            Glide.with(itemView.context)
//                .load(teamMember.url)
//                .placeholder(R.drawable.ic_launcher_background) // Add a placeholder image
//                .error(R.drawable.location_pin_svgrepo_com) // Add an error image
//                .transform(CircleCrop()) // Apply CircleCrop transformation here
//                .into(imageView)


            val imageUri = Uri.parse(teamMember.url)
            imageView.setImageURI(imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.teammemberlayout, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teamMember = teamMembers[position]
        holder.bind(teamMember)
    }

    override fun getItemCount(): Int {
        return teamMembers.size
    }
}
