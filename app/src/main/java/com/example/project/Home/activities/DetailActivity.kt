package com.example.project.Home.activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_detail)

            // Get data from intent
            val courtName = intent.getStringExtra("court_name") ?: "New Sports"
            val courtAddress = intent.getStringExtra("court_address") ?: "280 An Dương Vương, Phường 4, Quận 5"

            // Update UI with court data
            findViewById<TextView>(R.id.tvCourtName)?.text = courtName
            findViewById<TextView>(R.id.tvCourtAddress)?.text = courtAddress

            // Handle back button
            findViewById<ImageView>(R.id.iv_back)?.setOnClickListener {
                finish()
            }

        } catch (e: Exception) {
            Log.e("DetailActivity", "Error in onCreate", e)
        }
    }
}