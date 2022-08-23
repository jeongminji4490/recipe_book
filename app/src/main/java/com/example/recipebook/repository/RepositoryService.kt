package com.example.recipebook.repository

import com.example.recipebook.api.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface RepositoryService {
    suspend fun getRecipeList():  Flow<RecipeList?> // 레시피 목록 조회
    suspend fun getBookmarkList(): Flow<MutableMap<String, String>?> // 북마크한 레시피 목록 조회
    suspend fun getRecipe(name: String): Flow<RecipeList?> // 특정 레시피 정보 조회
    fun addBookmarkRecipe(name: String, ingredients: String, type: String) // 뷱마크한 레시피 목록에 레시피 추가
    fun deleteBookmarkRecipe(name: String) // 북마크한 레시피 목록에서 레시피 삭제
}