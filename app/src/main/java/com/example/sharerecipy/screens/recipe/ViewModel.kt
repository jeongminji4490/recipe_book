package com.example.sharerecipy.screens.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharerecipy.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repo: Repository,
    private val dataStore: DataStore
    ) : ViewModel() {

    var list : MutableLiveData<RecipeList> = MutableLiveData()
    var infos : MutableLiveData<RecipeInfo> = MutableLiveData()

    init {
        viewModelScope.launch { // 초기화 시 더 빨리 불러올 수 있음
            repo.getRecipes()
            list = repo.list
        }
    }

    fun getInfoTest(){
        viewModelScope.launch {
            val name = dataStore.recipeName.first()
            repo.getRecipeInfo(name)
            infos = repo.infos
        }
    }

    fun setRecipeName(name: String, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            dataStore.setRecipeName(name)
            openAndPopUp(RECIPE_DETAIL_SCREEN, RECIPE_SCREEN)
        }
    }
}
