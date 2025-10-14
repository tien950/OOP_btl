package com.example.project.Home.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Home.activities.BookingActivity
import com.example.project.Home.activities.DetailActivity
import com.example.project.Home.models.Court
import com.example.project.databinding.ListItemBinding

class SearchAdapter(private val courtList: List<Court>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(court: Court) {
            binding.title.text = court.getDisplayName()
            binding.address.text = court.getDisplayAddress()
            // Gán các dữ liệu khác vào đây (giờ, ảnh, logo...)
            // Ví dụ: binding.ivLogo.load(court.getDisplayImage()) // Cần thư viện Coil/Glide

            // Set click listener on the entire card to navigate to DetailActivity
            binding.root.setOnClickListener {
                Log.d("SearchAdapter", "=== CARD CLICK DETECTED ===")
                Log.d("SearchAdapter", "Card clicked for: ${court.getDisplayName()}")

                try {
                    val intent = Intent(binding.root.context, DetailActivity::class.java).apply {
                        putExtra("court_name", court.getDisplayName())
                        putExtra("court_address", court.getDisplayAddress())
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }

                    Log.d("SearchAdapter", "Starting DetailActivity with data:")
                    Log.d("SearchAdapter", "  - court_name: ${intent.getStringExtra("court_name")}")
                    Log.d("SearchAdapter", "  - court_address: ${intent.getStringExtra("court_address")}")

                    binding.root.context.startActivity(intent)
                    Log.d("SearchAdapter", "DetailActivity started successfully")

                } catch (e: Exception) {
                    Log.e("SearchAdapter", "Error starting DetailActivity", e)
                }
            }

            // Make sure the card is clickable
            binding.root.isClickable = true
            binding.root.isFocusable = true

            // Click listener for "ĐẶT LỊCH" button - prevent propagation
            binding.btnBook.setOnClickListener {
                Log.d("SearchAdapter", "Book button clicked")
                val context = binding.root.context
                val intent = Intent(context, BookingActivity::class.java).apply {
                    // Truyền thông tin sân cụ thể
                    putExtra("COURT_ID", court.id)
                    putExtra("COURT_NAME", court.getDisplayName())
                    putExtra("COURT_ADDRESS", court.getDisplayAddress())
                }
                context.startActivity(intent)
            }

            // Handle other buttons if they exist
            binding.btnHeart?.setOnClickListener {
                Log.d("SearchAdapter", "Heart button clicked")
                // Handle favorite functionality
            }

            binding.btnDirections?.setOnClickListener {
                Log.d("SearchAdapter", "Directions button clicked")
                // Handle directions functionality
            }

            binding.phone?.setOnClickListener {
                Log.d("SearchAdapter", "Phone clicked")
                // Handle phone functionality
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(courtList[position])
    }

    override fun getItemCount(): Int = courtList.size
}
