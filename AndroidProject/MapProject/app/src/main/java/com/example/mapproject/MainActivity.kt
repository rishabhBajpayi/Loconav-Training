package com.example.mapproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapproject.adapter.VDetailsAdapter
import com.example.mapproject.databinding.ActivityMainBinding
import com.example.mapproject.service.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    val CHANNEL_ID = "1st Channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.vehicleData.observe(this, {
            val viewId = binding.vehRecView
            val adapter = VDetailsAdapter(it)
            viewId.adapter = adapter
            viewId.layoutManager = LinearLayoutManager(this)
        })

        binding.notifBtn.setOnClickListener {
            sendNotification();
        }
    }

    private fun sendNotification() {
        val bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.notif_icon)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setLargeIcon(bitmap)
            .setSmallIcon(R.drawable.notif_icon)
            .setContentTitle("Notification")
            .setContentText("Testing notification and launcher icon")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        createNotificationChannel()

        NotificationManagerCompat.from(this).notify(1, builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}