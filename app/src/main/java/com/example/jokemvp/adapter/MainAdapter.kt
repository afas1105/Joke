package com.example.jokemvp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokemvp.R
import com.example.jokemvp.databinding.ItemRvCategoryBinding
import com.example.jokemvp.model.response.JokesData
import java.util.*

class MainAdapter(val callback: (from: Int, category: String, type: String) -> Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val dataSet: MutableList<String> = mutableListOf()
    private val dataSub: MutableList<JokesData> = mutableListOf()
    private var selectedPos = RecyclerView.NO_POSITION

    @SuppressLint("NotifyDataSetChanged")
    fun addList(items: List<String>) {
        dataSet.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addSub(items: List<JokesData>) {
        dataSub.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearSub() {
        dataSub.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swap(from: Int) {
        Collections.swap(dataSet,0, from)
        dataSub.clear()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemRvCategoryBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bindData(category: String){

            val adapter = SubMainAdapter{ content, type ->
                when(type){
                    "show" -> callback(adapterPosition, content, "show_dialog")
                    "add" -> callback(adapterPosition, category, "add")
                }
            }

            binding.rvDetail.adapter = adapter

            binding.apply {

                if (selectedPos == layoutPosition) {
                    ivDropdown.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                    rvDetail.visibility = View.VISIBLE
                    adapter.addList(dataSub)
                } else {
                    ivDropdown.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                    rvDetail.visibility = View.GONE
                    adapter.clear()
                }

                if (adapterPosition > 0)
                    btnGoToTop.visibility = View.VISIBLE
                else
                    btnGoToTop.visibility = View.INVISIBLE

                tvCategory.text = "${adapterPosition.plus(1)} \t $category"

                btnGoToTop.setOnClickListener {
                    callback(adapterPosition, category, "gotop")
                }

                binding.root.setOnClickListener {
                    if (selectedPos == layoutPosition) {
                        callback(adapterPosition, category, "detail_hide")
                        notifyItemChanged(selectedPos)
                        selectedPos = RecyclerView.NO_POSITION
                        notifyItemChanged(selectedPos)
                    } else {
                        callback(adapterPosition, category, "detail_hide")
                        callback(adapterPosition, category, "detail_show")
                        notifyItemChanged(selectedPos)
                        selectedPos = layoutPosition
                        notifyItemChanged(selectedPos)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemRvCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}