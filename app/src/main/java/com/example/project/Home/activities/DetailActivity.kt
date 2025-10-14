package com.example.project.Home.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DetailActivity", "onCreate called")

        try {
            // Use the original detail layout
            setContentView(R.layout.activity_detail)

            Log.d("DetailActivity", "Layout set successfully")

            // Get data from intent
            val courtName = intent.getStringExtra("court_name") ?: "New Sports"
            val courtAddress = intent.getStringExtra("court_address") ?: "280 An Dương Vương, Phường 4, Quận 5"

            Log.d("DetailActivity", "Court data - Name: $courtName, Address: $courtAddress")

            // Update UI with court data
            findViewById<TextView>(R.id.tvCourtName).text = courtName
            findViewById<TextView>(R.id.tvCourtAddress).text = courtAddress

            Log.d("DetailActivity", "UI updated with court data")

            // Handle back button - use the correct ID from the layout
            findViewById<android.widget.ImageView>(R.id.ivArrowBack).setOnClickListener {
                Log.d("DetailActivity", "Back button clicked")
                finish()
            }

            Log.d("DetailActivity", "DetailActivity setup completed successfully")

        } catch (e: Exception) {
            Log.e("DetailActivity", "Error in onCreate", e)
            e.printStackTrace()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DetailActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DetailActivity", "onResume called")
    }
}