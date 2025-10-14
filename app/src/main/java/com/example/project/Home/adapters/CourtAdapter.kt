package com.example.project.Home.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Home.models.Court
import com.example.project.Home.activities.DetailActivity
import com.example.project.databinding.ListItemBinding

class CourtAdapter(private val courtList: List<Court>) :
    RecyclerView.Adapter<CourtAdapter.CourtViewHolder>() {

    inner class CourtViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(court: Court) {
            binding.title.text = court.getDisplayName()
            binding.address.text = court.getDisplayAddress()

            // Set click listener on the MaterialCardView root
            binding.root.setOnClickListener {
                Log.d("CourtAdapter", "=== CARD CLICK DETECTED ===")
                Log.d("CourtAdapter", "Card clicked for: ${court.getDisplayName()}")
                Log.d("CourtAdapter", "Context: ${binding.root.context}")
                Log.d("CourtAdapter", "Context class: ${binding.root.context.javaClass.simpleName}")

                try {
                    val intent = Intent(binding.root.context, DetailActivity::class.java).apply {
                        putExtra("court_name", court.getDisplayName())
                        putExtra("court_address", court.getDisplayAddress())
                        // Add flags to ensure the activity starts properly
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }

                    Log.d("CourtAdapter", "Intent created with extras:")
                    Log.d("CourtAdapter", "  - court_name: ${intent.getStringExtra("court_name")}")
                    Log.d("CourtAdapter", "  - court_address: ${intent.getStringExtra("court_address")}")
                    Log.d("CourtAdapter", "About to start DetailActivity...")

                    binding.root.context.startActivity(intent)
                    Log.d("CourtAdapter", "startActivity() called successfully")

                } catch (e: Exception) {
                    Log.e("CourtAdapter", "=== ERROR STARTING ACTIVITY ===", e)
                    Log.e("CourtAdapter", "Error message: ${e.message}")
                    Log.e("CourtAdapter", "Error cause: ${e.cause}")
                }
            }

            // Make sure the card is clickable
            binding.root.isClickable = true
            binding.root.isFocusable = true

            // Prevent click propagation for specific buttons
            binding.btnHeart.setOnClickListener {
                Log.d("CourtAdapter", "Heart button clicked")
                // Handle heart button click separately
            }

            binding.btnDirections.setOnClickListener {
                Log.d("CourtAdapter", "Directions button clicked")
                // Handle map/directions button click separately
            }

            binding.btnBook.setOnClickListener {
                Log.d("CourtAdapter", "Book button clicked")
                // Handle booking button click separately
            }

            binding.phone.setOnClickListener {
                Log.d("CourtAdapter", "Phone clicked")
                // Handle phone call functionality here
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourtViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourtViewHolder, position: Int) {
        holder.bind(courtList[position])
    }

    override fun getItemCount(): Int = courtList.size
}
