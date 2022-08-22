package com.example.recipebook.repository

import com.example.recipebook.api.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface RepositoryService {
    suspend fun getRecipeList():  Flow<RecipeList?> // 레시피 목록 조회
    suspend fun getWishList(): Flow<MutableMap<String, String>?> // 찜목록 조회
    suspend fun getRecipe(name: String): Flow<RecipeList?> // 레시피 조회
    fun addWishRecipe(name: String, ingredients: String, type: String) // 찜목록에 레시피 추가
    fun deleteWishRecipe(name: String) // 찜목록에서 레시피 삭제
}