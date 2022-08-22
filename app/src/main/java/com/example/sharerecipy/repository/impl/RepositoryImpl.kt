package com.example.sharerecipy.repository.impl

import android.util.Log
import com.example.sharerecipy.BuildConfig
import com.example.sharerecipy.api.model.RecipeList
import com.example.sharerecipy.api.request.RecipeService
import com.example.sharerecipy.repository.RepositoryService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val recipeService: RecipeService
) : RepositoryService {

    // API
    private val keyId = BuildConfig.RECIPE_API_KEY
    private val serviceId = "COOKRCP01"
    private val dataType = "json"

    // 레시피 목록 조회
    override suspend fun getRecipeList(): Flow<RecipeList?> = flow {
        try {
            val response = recipeService.getRecipeList(keyId, serviceId, dataType)
            if (response.isSuccessful){
                val body = response.body()
                body?.let { emit(body) }
            }
        }catch (e: Exception){
            Log.e("$TAG getRecipes()", e.toString())
        }
    }

    // 로그인한 사용자의 찜목록 조회
    override suspend fun getWishList() = callbackFlow {
        val recipe = mutableMapOf<String, String>()
        val currentUser = Firebase.auth.currentUser
        val docName = currentUser?.email.toString()
        val db = Firebase.firestore

        val subCollection = db.collection("user").document(docName).collection("wish list")

        subCollection.get() // 하위 컬렉션의 모든 문서 가져오기
            .addOnSuccessListener { docs -> // 문서 조회 성공
                for (doc in docs){
                    if (doc.id != "sample"){ // sample 문서는 제외
                        recipe["name"] = doc.id
                        recipe["ingredients"] = doc.get("ingredients").toString()
                        recipe["type"] = doc.get("type").toString()
                        trySend(recipe)
                    }
                }
                close()
            }.addOnFailureListener { e ->
                Log.e(TAG, e.toString())
                close()
            }
        awaitClose()
    }

    // 로그인한 사용자의 찜목록에 레시피 추가
    override fun addWishRecipe(name: String, ingredients: String, type: String) {
        val currentUser = Firebase.auth.currentUser
        val docName = currentUser?.email.toString()
        val db = Firebase.firestore

        // 하위 컬렉션에서 name 문서 생성
        val docRef = db.collection("user").document(docName)
            .collection("wish list").document(name)
        val data = hashMapOf(
            "ingredients" to ingredients,
            "type" to type
        )
        docRef.set(data) // 문서에 저장
            .addOnSuccessListener {
                Log.i(TAG, "DocumentSnapshot added with ID: $it")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    // 로그인한 사용자의 찜목록에서 레시피 삭제
    override fun deleteWishRecipe(name: String) {
        val currentUser = Firebase.auth.currentUser
        val db = Firebase.firestore
        val docRef = db.collection("user").document(currentUser?.email.toString())
            .collection("wish list").document(name)
        docRef.delete()
    }

    // 레시피 조회
    override suspend fun getRecipe(name: String): Flow<RecipeList?> = flow {
        try {
            val response = recipeService.getRecipe(keyId, serviceId, dataType, name)
            if (response.isSuccessful){
                val body = response.body()
                body?.let { emit(body) }
            }
        }catch (e: Exception){
            Log.e("$TAG getInfoTest()", e.toString())
        }
    }

    companion object {
        const val TAG = "RepositoryImpl"
    }
}