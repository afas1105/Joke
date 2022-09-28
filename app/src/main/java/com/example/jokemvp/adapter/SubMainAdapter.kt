package com.example.jokemvp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokemvp.databinding.ItemRvDetailBinding
import com.example.jokemvp.model.response.JokesData

class SubMainAdapter(val callback: (String, type: String) -> Unit): RecyclerView.Adapter<SubMainAdapter.SubMainViewHolder>() {

    private var data: MutableList<JokesData> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(items: List<JokesData>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    inner class SubMainViewHolder(private val binding: ItemRvDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: JokesData){
            binding.tvContent.text = content.joke

            if (data.size == 6) {
                binding.tvAdd.visibility = View.GONE
            } else {
                if (adapterPosition == data.size - 1) binding.tvAdd.visibility = View.VISIBLE
                else binding.tvAdd.visibility = View.GONE
            }

            binding.tvContent.setOnClickListener {
                callback(content.joke ?: "", "show")
            }

            binding.tvAdd.setOnClickListener {
                callback(content.category ?: "", "add")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMainAdapter.SubMainViewHolder {
        return SubMainViewHolder(ItemRvDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SubMainAdapter.SubMainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}