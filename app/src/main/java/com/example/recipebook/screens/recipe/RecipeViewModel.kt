package com.example.recipebook.screens.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.*
import com.example.recipebook.api.model.Recipe
import com.example.recipebook.api.model.RecipeList
import com.example.recipebook.repository.RepositoryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val service: RepositoryService,
    private val dataStore: DataStore
) : ViewModel() {

    var recipeList: MutableState<RecipeList?> = mutableStateOf(null) // 레시피 목록
    var bookmarkList = mutableStateMapOf<String, List<String>>() // 북마크한 레시피 목록
    var recipe: MutableState<Recipe?> = mutableStateOf(null) // 레시피
    var category: MutableState<String> = mutableStateOf("") // 레시피 종류

    suspend fun getRecipeList() { // 레시피 목록 조회
        viewModelScope.launch {
            service.getRecipeList().collect() {
                recipeList.value = it
            }
        }
    }

    suspend fun getBookmarkList() { // 북마크한 레시피 목록 조회
        viewModelScope.launch {
            service.getBookmarkList().collect() {
                if (it != null) {
                    bookmarkList[it["name"].toString()] =
                        listOf(it["ingredients"].toString(), it["type"].toString())
                }
            }

        }
    }

    fun getRecipe() { // 레시피 조회
        viewModelScope.launch {
            val name = dataStore.recipeName.first()
            val newString = name.replace(" ", "_") // 레시피명에서 공백은 '_'로 처리해야함
            var convertedName = newString
            if (newString.contains("_&")) // '&'이 포함된 레시피명은 '&'이전 문자열만으로 조회 가능
                convertedName = newString.split("_&")[0]

            service.getRecipe(convertedName).collect() {
                if (it != null){
                    recipe.value = it.list.recipes.first()
                }
            }
        }
    }

    fun addBookmarkRecipe(name: String, ingredients: String, type: String) { // 북마크한 레시피 목록에 레시피 추가
        service.addBookmarkRecipe(name, ingredients, type)
    }

    fun deleteBookmarkRecipe(name: String) { // 북마크한 레시피 목록에서 레시피 삭제
        service.deleteBookmarkRecipe(name)
    }

    fun getRecipeType() { // 레시피 종류 조회
        viewModelScope.launch {
            category.value = dataStore.typeName.first()
        }
    }

    fun setRecipeName(name: String, openScreen: (String) -> Unit) { // 레시피명 저장
        viewModelScope.launch {
            dataStore.setRecipeName(name)
            openScreen(RECIPE_SCREEN) // name 에 해당하는 레시피 조리방법 조회
        }
    }

    companion object {
        const val TAG = "RecipeViewModel"
    }
}
