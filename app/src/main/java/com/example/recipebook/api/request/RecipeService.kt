package com.example.recipebook.api.request

import com.example.recipebook.api.model.RecipeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {

    // 특정 레시피의 정보 조회
    @GET("api/{keyId}/{serviceId}/{dataType}/1/500/RCP_NM={name}")
    suspend fun getRecipe(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String,
        @Path("name") name: String
    ): Response<RecipeList>

    // 500개의 레시피 목록 조회
    @GET("api/{keyId}/{serviceId}/{dataType}/1/500/")
    suspend fun getRecipeList(
        @Path("keyId") keyId : String,
        @Path("serviceId") serviceId : String,
        @Path("dataType") dataType : String
    ): Response<RecipeList>
}