package com.example.sharerecipy.repository.impl

import android.util.Log
import com.example.sharerecipy.BuildConfig
import com.example.sharerecipy.DataState
import com.example.sharerecipy.api.model.RecipeList
import com.example.sharerecipy.api.request.RecipeService
import com.example.sharerecipy.repository.RepositoryService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val recipeService: RecipeService
) : RepositoryService {

    private val keyId = BuildConfig.RECIPE_API_KEY
    private val serviceId = "COOKRCP01"
    private val dataType = "json"

    override suspend fun getRecipe(): Flow<RecipeList?> = flow {
        try {
            val response = recipeService.getRecipes(keyId, serviceId, dataType)
            if (response.isSuccessful){
                val body = response.body()
                body?.let { emit(body) }
            }
        }catch (e: Exception){
            Log.e("$TAG getRecipes()", e.toString())
            emit(null)
        }
    }

    override suspend fun getWishList() = callbackFlow {
        var list = ""
        val currentUser = Firebase.auth.currentUser // 로그인한 사용자
        val db = Firebase.firestore
        val docRef = db.collection("user").document(currentUser?.email.toString()).get()

        docRef.addOnSuccessListener {
            list = it.data?.get("wish list").toString()
            trySend(list)
            close()
        }

        awaitClose()
    }

    override fun addWishRecipe(name: String) {
        val currentUser = Firebase.auth.currentUser // 로그인한 사용자
        val db = Firebase.firestore
        val docRef = db.collection("user").document(currentUser?.email.toString())

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    docRef.update(
                        "wish list", FieldValue.arrayUnion(name)
                    )
                } else {
                    Log.d(TAG, "no such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    override fun deleteWishRecipe(name: String) {
        val currentUser = Firebase.auth.currentUser // 로그인한 사용자
        val db = Firebase.firestore
        val docRef = db.collection("user").document(currentUser?.email.toString())

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    docRef.update(
                        "wish list", FieldValue.arrayRemove(name)
                    )
                } else {
                    Log.d(TAG, "no such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    companion object {
        const val TAG = "RepositoryImpl"
    }
}