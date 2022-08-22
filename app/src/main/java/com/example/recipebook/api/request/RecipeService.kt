package com.example.recipebook.api.request

import com.example.recipebook.api.model.RecipeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {

    @GET("api/{keyId}/{serviceId}/{dataType}/1/500/RCP_NM={name}")
    suspend fun getRecipe(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String,
        @Path("name") name: String
    ): Response<RecipeList>

    @GET("api/{keyId}/{serviceId}/{dataType}/1/500/")
    suspend fun getRecipeList(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Response<RecipeList>
}