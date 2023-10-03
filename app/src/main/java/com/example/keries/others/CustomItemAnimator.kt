package com.example.keries.others

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlin.math.abs
import kotlin.math.abs

class CustomItemAnimator : DefaultItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        if (oldHolder != newHolder) {
            val oldView = oldHolder.itemView
            val newView = newHolder.itemView

            val rotation = calculateRotation(oldView, newView)
            newView.rotationY = rotation
        }
        return false
    }

    private fun calculateRotation(oldView: View, newView: View): Float {
        val recyclerView = newView.parent as RecyclerView
        val centerX = recyclerView.width / 2f
        val centerY = recyclerView.height / 2f
        val distanceFromCenter = abs(newView.left + newView.width / 2 - centerX)
        val maxRotation = 20f // Adjust this value for the desired rotation angle

        return maxRotation * (distanceFromCenter / centerX)
    }
}
