package com.example.keries.classes

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.keries.R

class ImageViewerDialog(context: Context, private val imageUrl: String) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.image_viewer_layout)

        val imageView = findViewById<ImageView>(R.id.enlargedImageView)
        val closeButton = findViewById<ImageView>(R.id.closeButton)
        window?.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent))
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.image_svgrepo_com)
            .error(R.drawable.image_svgrepo_com)
            .transform(CircleCrop())
            .into(imageView)

        closeButton.setOnClickListener {
            dismiss()
        }
    }
}
