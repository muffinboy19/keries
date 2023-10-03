package com.example.keries.others

import android.content.Context
import android.view.View
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.fragments.Events
import kotlin.math.abs

class CustomLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {
    private val maxScale = 0.8f // Adjust this value for the desired maximum scaling

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
//        scaleItems()
    }

//    private fun scaleItems() {
////        val recyclerView = getRecyclerView()
////        val centerX = recyclerView.width / 2f
//
//        for (i in 0 until childCount) {
//            val child = getChildAt(i) ?: continue
//            val childCenterX = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
//            val distanceFromCenter = Math.abs(childCenterX - centerX)
//            val scale = Math.max(1 - distanceFromCenter / centerX, maxScale)
//            child.scaleX = scale
//            child.scaleY = scale
//        }
//    }

//    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
//        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
//        scaleItems()
//        return scrolled
//    }
}




class ScaleItemDecoration(private val maxScale: Float = 0.8f) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val recyclerViewWidth = parent.width
        val itemWidth = view.width
        val itemPosition = parent.getChildAdapterPosition(view)
        val offset = (recyclerViewWidth - itemWidth) / 2

        if (itemPosition == 0) {
            // Center the first item
            outRect.left = offset
        } else if (itemPosition == state.itemCount - 1) {
            // Center the last item
            outRect.right = offset
        }
    }
}

