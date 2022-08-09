package com.example.sharerecipy.model.service.impl

//class RecipeServiceImpl @Inject constructor(): RecipeService {
//
//    private val keyId = "af2bd97db6b846529d0e"
//    private val serviceId = "COOKRCP01"
//    private val dataType = "json"
//    var list : MutableLiveData<RecipeList> = MutableLiveData()
//    var infos : MutableLiveData<RecipeInfoList> = MutableLiveData()
//
//    override fun getRecipes() {
//        RetrofitClient.recipeService.getListTest(keyId, serviceId, dataType)
//            .enqueue(object : Callback<RecipeList> {
//                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
//                    if (response.isSuccessful) {
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
//
//    override fun getRecipeInfo() {
//        RetrofitClient.recipeService.getRecipeInfo(keyId, serviceId, dataType)
//            .enqueue(object: Callback<RecipeInfoList> {
//                override fun onResponse(call: Call<RecipeInfoList>, response: Response<RecipeInfoList>) {
//                    if (response.isSuccessful) {
//                        response.body()?.let {
//                            infos.value = it
//                        }
//                    } else {
//                        Log.e("infos", response.message())
//                    }
//                }
//                override fun onFailure(call: Call<RecipeInfoList>, t: Throwable) {
//                    Log.e("infos", t.toString())
//                }
//            })
//    }
//
//    companion object {
//        const val TAG = "REPOSITORY"
//    }
//}