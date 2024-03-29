package com.example.recipebook.repository.impl

import android.util.Log
import com.example.recipebook.BuildConfig
import com.example.recipebook.api.model.RecipeList
import com.example.recipebook.api.request.RecipeService
import com.example.recipebook.repository.RepositoryService
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

    // 북마크한 레시피 목록 조회
    override suspend fun getBookmarkList() = callbackFlow {
        val recipe = mutableMapOf<String, String>()
        val currentUser = Firebase.auth.currentUser
        val docName = currentUser?.email.toString()
        val db = Firebase.firestore

        val subCollection = db.collection("user").document(docName).collection("wish list")

        subCollection.get() // 하위 컬렉션의 모든 문서 가져오기
            .addOnSuccessListener { docs -> // 문서 조회 성공
                for (doc in docs){
                    if (doc.id != "sample"){ // sample 문서는 제외
                        recipe["name"] = doc.id // 레시피명
                        recipe["ingredients"] = doc.get("ingredients").toString() // 재료
                        recipe["type"] = doc.get("type").toString() // 종류
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

    // 북마크한 레시피 목록에 레시피 추가
    override fun addBookmarkRecipe(name: String, ingredients: String, type: String) {
        val currentUser = Firebase.auth.currentUser
        val docName = currentUser?.email.toString()
        val db = Firebase.firestore

        // 하위 컬렉션에서 레시피명으로 문서 생성
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

    // 북마크한 레시피 목록에서 레시피 삭제
    override fun deleteBookmarkRecipe(name: String) {
        val currentUser = Firebase.auth.currentUser
        val db = Firebase.firestore
        val docRef = db.collection("user").document(currentUser?.email.toString())
            .collection("wish list").document(name) // 레시피명에 해당하는 문서 삭제
        docRef.delete()
    }

    // 특정 레시피 정보 조회
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