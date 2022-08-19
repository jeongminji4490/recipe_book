package com.example.sharerecipy.api.request

import com.example.sharerecipy.api.model.RecipeList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

//    @GET("api/{keyId}/{serviceId}/{dataType}/1/200/")
//    suspend fun getRecipeInfoTest(
//        @Path("keyId") keyId : String,
//        @Path("serviceId") serviceId : String,
//        @Path("dataType") dataType : String
//    ): Response<RecipeList>

    @GET("api/{keyId}/{serviceId}/{dataType}/1/200/")
    suspend fun getRecipes(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Response<RecipeList>
}