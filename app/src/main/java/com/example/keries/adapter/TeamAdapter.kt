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
import com.example.keries.dataClass.TeamMember
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class TeamAdapter(private val teamMembers: List<TeamMember>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val wingTextView: TextView = itemView.findViewById(R.id.desginations)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val shimmerLayout:ShimmerFrameLayout = itemView.findViewById(R.id.shimmer)


        fun bind(teamMember: TeamMember) {
            nameTextView.text = teamMember.name
            if(teamMember.no>42){
                wingTextView.text = "EXECUTIVE"
            }
            else{
                wingTextView.text = "HEAD"
            }

//            val shimmer = Shimmer.AlphaHighlightBuilder()
//                .setDuration(3000)
//                .setBaseAlpha(.7f)
//                .setHighlightAlpha(.9f)
//                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
//                .build()

            Glide.with(itemView.context)
                .load(teamMember.url)
                .placeholder(R.drawable.ic_launcher_background) // Add a placeholder image
                .error(R.drawable.location_pin_svgrepo_com) // Add an error image
                .transform(CircleCrop()) // Apply CircleCrop transformation here
                .into(imageView)

//            imageView.load(teamMember.url) {
//                placeholder(R.drawable.ic_launcher_background) // Add a placeholder image
//                error(R.drawable.location_pin_svgrepo_com) // Add an error image
//                transformations(CircleCropTransformation()) // Apply CircleCrop transformation here
//            }

//
//            val displayImageOptions = DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_launcher_background) // Add a placeholder image
//                .showImageForEmptyUri(R.drawable.location_pin_svgrepo_com) // Add an error image
//                .showImageOnFail(R.drawable.location_pin_svgrepo_com) // Add an error image
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)


//            val imageUri = Uri.parse(teamMember.url)
//            imageView.setImageURI(imageUri)
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
//        holder.shimmerLayout.setShimmer()
    }

    override fun getItemCount(): Int {
        return teamMembers.size
    }
}
