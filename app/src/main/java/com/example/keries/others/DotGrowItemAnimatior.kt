package com.example.keries.others

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class DotGrowItemAnimator : DefaultItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        if (holder != null) {
            val scaleX = ValueAnimator.ofFloat(0f, 1f)
            val scaleY = ValueAnimator.ofFloat(0f, 1f)

            scaleX.duration = 300
            scaleY.duration = 300

            scaleX.addUpdateListener { animation ->
                holder.itemView.scaleX = animation.animatedValue as Float
            }

            scaleY.addUpdateListener { animation ->
                holder.itemView.scaleY = animation.animatedValue as Float
            }

            scaleX.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dispatchAddFinished(holder)
                    holder.itemView.scaleX = 1f
                }
            })

            scaleY.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dispatchAddFinished(holder)
                    holder.itemView.scaleY = 1f
                }
            })

            scaleX.start()
            scaleY.start()

            return true
        }
        return super.animateAdd(holder)
    }
}
