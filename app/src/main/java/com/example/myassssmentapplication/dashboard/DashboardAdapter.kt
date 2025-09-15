package com.example.myassssmentapplication.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.databinding.ItemEntityBinding

class DashboardAdapter(
    private val onClick: (Entity) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.VH>() {

    private val data = mutableListOf<Entity>()

    fun updateItems(items: List<Entity>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEntityBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding, onClick)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    class VH(
        private val binding: ItemEntityBinding,
        private val onClick: (Entity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Entity) {
            binding.tvTitle.text = item.title
            binding.tvSubtitle.text = item.subtitle ?: "From: ${item.ownerKeypass}"
            binding.root.setOnClickListener { onClick(item) }
        }
    }
}
