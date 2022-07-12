package com.example.sharerecipy

import androidx.compose.runtime.Composable
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://openapi.foodsafetykorea.go.kr/"

    var gson= GsonBuilder().setLenient().create()

    val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val recipeService : RecipeService by lazy { retrofit.create(RecipeService::class.java) }
}