package com.example.sharerecipy

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val keyId = "af2bd97db6b846529d0e" // 인증키 하드코딩
    private val serviceId = "COOKRCP01"
    private val dataType = "json"
    var list : MutableLiveData<RecipeList> = MutableLiveData()
    var infos : MutableLiveData<RecipeInfo> = MutableLiveData()

    fun getRecipes() {
        RetrofitClient.recipeService.getListTest(keyId, serviceId, dataType)
            .enqueue(object : Callback<RecipeList> {
                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                    if (response.isSuccessful) {
                        //progressState = 0
                        response.body()?.let {
                            list.value = it
                        }
                    } else {
                        Log.e("recipe", response.message())
                    }
                }
                override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                    Log.e("recipe", t.toString())
                }
            })
    }

    fun getRecipeInfo(name: String) {
        //Log.e(TAG, name)
        RetrofitClient.recipeService.getRecipeInfo(keyId, serviceId, dataType, name)
            .enqueue(object: Callback<RecipeInfoList> {
                override fun onResponse(call: Call<RecipeInfoList>, response: Response<RecipeInfoList>) {
                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            infos.value = it
//                        }
                        response.body()?.let {
                            for (i in it.list.recipeInfo.indices){
                                Log.e(TAG, it.list.recipeInfo[i].name)
                                if (name == it.list.recipeInfo[i].name){
                                    infos.value = it.list.recipeInfo[i]
                                    break
                                }
                            }
                        }
                    } else {
                        Log.e("infos", response.message())
                    }
                }
                override fun onFailure(call: Call<RecipeInfoList>, t: Throwable) {
                    Log.e("infos", t.toString())
                }
            })
    }

    companion object {
        const val TAG = "REPOSITORY"
    }
}