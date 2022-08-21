package com.example.sharerecipy.screens.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharerecipy.*
import com.example.sharerecipy.api.model.Recipe
import com.example.sharerecipy.api.model.RecipeList
import com.example.sharerecipy.repository.RepositoryService
import com.example.sharerecipy.repository.impl.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val service: RepositoryService,
    private val dataStore: DataStore
) : ViewModel() {

    var recipeList: MutableState<RecipeList?> = mutableStateOf(null)
    var wishList = mutableStateMapOf<String, List<String>>()
    var info: MutableState<Recipe?> = mutableStateOf(null)
    //var infoTest: MutableState<Inf?> = mutableStateOf(null)
    var type: MutableState<String> = mutableStateOf("")

    suspend fun getRecipe() { // 레시피 목록 조회
        viewModelScope.launch {
            service.getRecipe().collect() {
                recipeList.value = it
            }
//            service.getRecipe().onEach {
//                recipeList.value = it
//            }.launchIn(viewModelScope)
        }
    }

    suspend fun getWishList() { // 찜한 레시피 조회
        viewModelScope.launch {
            service.getWishList().collect() {
                if (it != null) {
                    wishList[it["name"].toString()] =
                        listOf(it["ingredients"].toString(), it["type"].toString())
                }
            }

        }
    }

//    fun getInfo() { // 특정 레시피의 조리 방법 조회
//        viewModelScope.launch {
//            val name = dataStore.recipeName.first()
//            service.getRecipe().collect() {
//                if (it != null) {
//                    for (i in it.list.recipes.indices) {
//                        if (name == it.list.recipes[i].name) {
//                            info.value = it.list.recipes[i]
//                            break
//                        }
//                    }
//                }
//            }
//        }
//    }

    fun getInfo() {
        viewModelScope.launch {
            val name = dataStore.recipeName.first()
            val newString = name.replace(" ", "_")
            var convertedName = newString
            if (newString.contains("_&"))
                convertedName = newString.split("_&")[0]

            service.getInfo(convertedName).collect() {
                if (it != null){
                    info.value = it.list.recipes.first()
                }
            }
        }
    }

    fun addWishRecipe(name: String, ingredients: String, type: String) { // 레시피 찜목록에 추가
        service.addWishRecipe(name, ingredients, type)
    }

    fun deleteWishRecipe(name: String) { // 레시피 찜목록에서 삭제
        service.deleteWishRecipe(name)
    }

    fun getRecipeType() { // 레시피 종류 조회
        viewModelScope.launch {
            type.value = dataStore.typeName.first()
        }
    }

    fun setRecipeName(name: String, openScreen: (String) -> Unit) { // 레시피명 저장
        viewModelScope.launch {
            dataStore.setRecipeName(name)
            openScreen(RECIPE_DETAIL_SCREEN) // name 에 해당하는 레시피 조리방법 조회
        }
    }

    companion object {
        const val TAG = "RecipeViewModel"
    }
}
