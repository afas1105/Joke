package com.example.jokemvp.model

import com.example.jokemvp.model.response.CategoriesResponses
import com.example.jokemvp.model.response.JokeResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    companion object {

        var retrofitService: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofitService == null){
                synchronized(this) {
                    retrofitService = Retrofit.Builder()
                        .baseUrl("https://v2.jokeapi.dev/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return retrofitService!!
        }
    }

    @GET("categories")
    fun getCategories(): Call<CategoriesResponses>

    @GET("joke/{category}")
    fun getJokeData(
        @Path("category") category: String,
        @Query("type") type: String = "single",
        @Query("amount") amount: Int = 2
    ): Call<JokeResponse>
}