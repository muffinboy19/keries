package com.example.keries.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationManagerCompat
import com.example.keries.fragments.Events
import com.example.keries.fragments.Home
import com.example.keries.fragments.More
import com.example.keries.R
import com.example.keries.fragments.Schedule
import com.example.keries.fragments.Shop

class BaseHome : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Intent>

    // Listener for handling navigation item selection
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_events -> {
                loadFragment(Events()) // Load Events Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_schedule -> {
                loadFragment(Schedule()) // Load Schedule Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                loadFragment(Home()) // Load Home Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shop -> {
                loadFragment(Shop()) // Load Shop Fragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                loadFragment(More()) // Load More Fragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase and subscribe to a topic for notifications
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().subscribeToTopic("notification")

        // Set up the status bar and layout
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_base_home)

        // Initialize views and load the Home Fragment by default
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(Home())
        bottomNavigationView.selectedItemId = R.id.navigation_home

        // Initialize the launcher for requesting notification permission
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Permission granted, continue with your app.
                // Initialize your bottom navigation and fragments here.
            } else {
                // Permission denied, you can handle this case as needed.
                showPermissionDeniedDialog()
            }
        }

        // Check if notification permission is granted, if not, request it
        if (!isNotificationPermissionGranted()) {
            requestNotificationPermission()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isNotificationPermissionGranted(): Boolean {
        return NotificationManagerCompat.from(this).areNotificationsEnabled()
    }

    private fun requestNotificationPermission() {
        val intent = Intent()
        intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        requestPermissionLauncher.launch(intent)
    }

    private fun showPermissionDeniedDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Notification Permission Required")
        builder.setMessage("To use this app, please grant notification permissions.")
        builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setCancelable(false)
        builder.show()
    }
}
