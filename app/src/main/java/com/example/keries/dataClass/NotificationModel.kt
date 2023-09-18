package com.example.keries.dataClass

import androidx.lifecycle.ViewModel

data class NotificationModel(
    val title: String,
    val imageUrl: String, // You can use this for the image URL
    val timestamp: Long
)

class NotificationViewModel : ViewModel() {
    val notifications = mutableListOf<NotificationModel>()
}
