package com.example.jokemvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jokemvp.adapter.MainAdapter
import com.example.jokemvp.model.response.CategoriesResponses
import com.example.jokemvp.databinding.ActivityMainBinding
import com.example.jokemvp.model.response.JokeResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()

        presenter.onGetCategories()

        binding.swipe.setOnRefreshListener {
            adapter.clearList()
            adapter.clearSub()
            presenter.onGetCategories()
        }
    }

    private fun setupAdapter(){
        adapter = MainAdapter { position, category , type ->
            when (type) {
                "gotop" -> adapter.swap(position)
                "detail_show" -> {presenter.onGetJokeData(category)}
                "detail_hide" -> {adapter.clearSub()}
                "add" -> {presenter.onGetJokeData(category)}
                "show_dialog" -> {
                    MaterialAlertDialogBuilder(this,
                    com.google.android.material.R.style.AlertDialog_AppCompat)
                    .setMessage(category)
                    .setNegativeButton(resources.getString(R.string.ok)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()}
            }
        }
        binding.rvCategory.adapter = adapter
        binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onLoading(isShow: Boolean) {}

    override fun successGetJokeData(data: JokeResponse?) {
        binding.swipe.isRefreshing = false
        data?.jokes?.let { adapter.addSub(it) }
    }

    override fun successGetCategories(data: CategoriesResponses?) {
        binding.swipe.isRefreshing = false
        data?.categories?.let { adapter.addList(it) }
    }
}