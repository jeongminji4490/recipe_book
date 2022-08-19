package com.example.sharerecipy.repository

import com.example.sharerecipy.api.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface RepositoryService {
    suspend fun getRecipe():  Flow<RecipeList?>
    suspend fun getWishList(): Flow<String?>
    fun addWishRecipe(name: String, ingredients: String)
    fun deleteWishRecipe(name: String, ingredients: String)
}