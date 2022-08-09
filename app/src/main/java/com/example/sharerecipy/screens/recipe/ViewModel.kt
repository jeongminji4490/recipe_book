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
    //var infos : MutableLiveData<RecipeInfoList> = MutableLiveData()
    var infos : MutableLiveData<RecipeInfo> = MutableLiveData()

    // 이걸 굳이 한꺼번에 초기화해야할까?
    init {
        repo.getRecipes()
        //repo.getRecipeInfo()
        list = repo.list
        //infos = repo.infos
    }

    fun getInfoTest(){
        viewModelScope.launch {
            val name = dataStore.recipeName.first()
            repo.getRecipeInfo(name)
            infos = repo.infos
        }
    }

    // datastore
    fun setRecipeName(name: String, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            dataStore.setRecipeName(name)
            openAndPopUp(RECIPE_DETAIL_SCREEN, RECIPE_SCREEN)
        }
    }
}
