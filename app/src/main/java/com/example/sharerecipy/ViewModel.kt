package com.example.sharerecipy

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repo: Repository
    ) : ViewModel() {

    var list : MutableLiveData<RecipeList> = MutableLiveData()
    var infos : MutableLiveData<RecipeInfoList> = MutableLiveData()

    // 이걸 굳이 한꺼번에 초기화해야할까?
    init {
        repo.getRecipes()
        repo.getRecipeInfo()
        list = repo.list
        infos = repo.infos
    }
}
