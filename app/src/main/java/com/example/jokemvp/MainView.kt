package com.example.jokemvp

import com.example.jokemvp.model.response.CategoriesResponses
import com.example.jokemvp.model.response.JokeResponse

interface MainView {
    fun onLoading(isShow: Boolean)
    fun successGetJokeData(data: JokeResponse?)
    fun successGetCategories(data: CategoriesResponses?)
}