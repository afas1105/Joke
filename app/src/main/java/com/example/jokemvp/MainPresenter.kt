package com.example.jokemvp

import com.example.jokemvp.model.response.CategoriesResponses
import com.example.jokemvp.model.RetrofitService
import com.example.jokemvp.model.response.JokeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view: MainView?) {

    var retrofitInterface: RetrofitService = RetrofitService.getInstance().create(RetrofitService::class.java)

    fun onGetCategories(){
        view?.onLoading(true)
        val request = retrofitInterface.getCategories()
        request.enqueue(object : Callback<CategoriesResponses>{
            override fun onResponse(
                call: Call<CategoriesResponses>,
                response: Response<CategoriesResponses>
            ) {
                view?.onLoading(false)
                if (response.isSuccessful) view?.successGetCategories(response.body())
            }

            override fun onFailure(call: Call<CategoriesResponses>, t: Throwable) {
                view?.onLoading(false)
            }
        })
    }

    fun onGetJokeData(category: String){
        view?.onLoading(true)
        val request = retrofitInterface.getJokeData(category = category)
        request.enqueue(object : Callback<JokeResponse>{
            override fun onResponse(call: Call<JokeResponse>, response: Response<JokeResponse>) {
                view?.onLoading(false)
                if (response.isSuccessful) view?.successGetJokeData(response.body())
            }

            override fun onFailure(call: Call<JokeResponse>, t: Throwable) {
                view?.onLoading(false)
            }
        })
    }
}