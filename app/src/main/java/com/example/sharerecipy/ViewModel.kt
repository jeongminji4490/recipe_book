package com.example.sharerecipy

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    val keyId = "af2bd97db6b846529d0e"
    val serviceId = "COOKRCP01"
    val dataType = "json"
    var list = mutableStateListOf<RecipeList>()

    fun getRecipeList(){
        viewModelScope.launch {
            RetrofitClient.recipeService.getList(keyId, serviceId, dataType)
                .enqueue(object : Callback<RecipeList> {
                    override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                        if (response.isSuccessful){
                            Log.e("recipe", "success")
                            response.body()?.let {
                                list.add(it)
                            }
                        }else{
                            Log.e("recipe", "failed")
                        }
                    }
                    override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                        Log.e("recipe", t.toString())
                    }
                })
        }
    }
}