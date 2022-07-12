package com.example.sharerecipy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET("api/{keyId}/{serviceId}/{dataType}/1/200")
    fun getList(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Call<RecipeList>
}