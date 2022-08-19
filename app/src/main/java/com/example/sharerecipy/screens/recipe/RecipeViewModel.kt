package com.example.sharerecipy.screens.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
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
    private val dataStore: DataStore,
) : ViewModel() {

    var recipeList: MutableState<RecipeList?> = mutableStateOf(null)
    var wishList = mutableStateMapOf<String, String>()
    var methodList = mutableStateListOf<String>()
    var info: MutableState<Recipe?> = mutableStateOf(null)

    // onEach vs collect
    suspend fun getRecipe() {
        viewModelScope.launch {
            service.getRecipe().onEach {
                recipeList.value = it
            }.launchIn(viewModelScope)
        }
    }

    suspend fun getWishList() {
        viewModelScope.launch {
            service.getWishList().collect() {
                //val newString = it?.replace("[", "")?.replace("]", "")?.split(", ")
                val newString = it?.split("}, ")
                newString?.forEach { string ->
                    val list = string.replace("[{", "").replace("{", "").replace("}]", "").split("=")
                    wishList[list[0]] = list[1]
                    //methodList.add(list[1])
                }
//                newString?.forEach { name ->
//                    wishList.add(name)
//                }
            }
        }
    }

    fun getInfo() {
        viewModelScope.launch {
            val name = dataStore.recipeName.first()
            service.getRecipe().collect() {
                if (it != null) {
                    for (i in it.list.recipes.indices) {
                        if (name == it.list.recipes[i].name) {
                            info.value = it.list.recipes[i]
                            break
                        }
                    }
                }
            }
        }
    }

    fun addWishRecipe(name: String, ingredients: String) {
        service.addWishRecipe(name, ingredients)
    }

    fun deleteWishRecipe(name: String,  ingredients: String) {
        service.deleteWishRecipe(name, ingredients)
    }

    fun setRecipeName(name: String, openScreen: (String) -> Unit,) {
        viewModelScope.launch {
            dataStore.setRecipeName(name)
            //openAndPopUp(RECIPE_DETAIL_SCREEN, RECIPE_SCREEN)
            openScreen(RECIPE_DETAIL_SCREEN)
        }
    }

    companion object {
        const val TAG = "RecipeViewModel"
    }
}
