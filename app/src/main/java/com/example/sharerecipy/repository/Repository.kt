package com.example.sharerecipy.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sharerecipy.BuildConfig
import com.example.sharerecipy.api.model.Recipe
import com.example.sharerecipy.api.model.RecipeList
import com.example.sharerecipy.api.request.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeService: RecipeService
) {
    private val keyId = BuildConfig.RECIPE_API_KEY // 인증키 하드코딩
    private val serviceId = "COOKRCP01"
    private val dataType = "json"
    var list: MutableLiveData<RecipeList> = MutableLiveData()
    var infos: MutableLiveData<Recipe> = MutableLiveData()

    suspend fun getRecipes() {
        try {
            val response = recipeService.getRecipes(keyId, serviceId, dataType)
            val body = response.body()
            body?.let {
                list.value = it
            }
        } catch (e: Exception) {
            Log.e("$TAG getRecipes()", e.toString())
        }
    }

//    fun getRecipes() {
//        RetrofitClient.recipeService.getListTest(keyId, serviceId, dataType)
//            .enqueue(object : Callback<RecipeList> {
//                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
//                    if (response.isSuccessful) {
//                        //progressState = 0
//                        response.body()?.let {
//                            list.value = it
//                        }
//                    } else {
//                        Log.e("recipe", response.message())
//                    }
//                }
//                override fun onFailure(call: Call<RecipeList>, t: Throwable) {
//                    Log.e("recipe", t.toString())
//                }
//            })
//    }

//    suspend fun getRecipeInfoTest(name: String) {
//        try {
//            val response = RetrofitClient.recipeService.getRecipeInfoTest(keyId, serviceId, dataType)
//            val body = response.body()
//            body?.let {
//                for (i in it.list.recipeInfo.indices){
//                    Log.e(TAG, it.list.recipeInfo[i].name)
//                    if (name == it.list.recipeInfo[i].name){
//                        infos.value = it.list.recipeInfo[i]
//                        break
//                    }
//                }
//            }
//        }catch (e: Exception) {
//            Log.e("$TAG getRecipeInfoTest()", e.toString())
//        }
//    }

    fun getRecipeInfo(name: String) {
        recipeService.getRecipeInfo(keyId, serviceId, dataType)
            .enqueue(object : Callback<RecipeList> {
                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            for (i in it.list.recipes.indices) {
                                Log.e(TAG, it.list.recipes[i].name)
                                if (name == it.list.recipes[i].name) {
                                    infos.value = it.list.recipes[i]
                                    break
                                }
                            }
                        }
                    } else {
                        Log.e("infos", response.message())
                    }
                }

                override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                    Log.e("infos", t.toString())
                }
            })
    }

    companion object {
        const val TAG = "REPOSITORY"
    }
}