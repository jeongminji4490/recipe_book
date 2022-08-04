package com.example.sharerecipy

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {
    @GET("api/{keyId}/{serviceId}/{dataType}/1/200/")
    fun getRecipeInfo(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Call<RecipeInfoList>

    @GET("api/{keyId}/{serviceId}/{dataType}/1/200/")
    fun getListTest(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Call<RecipeList>
}