package com.example.sharerecipy

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStore(private val context: Context) {
    private val Context.datastore by preferencesDataStore(name = "datastore")
    private val key = stringPreferencesKey("RECIPE_NAME")

    val recipeName : Flow<String> = context.datastore.data
        .catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }.map {
            it[key] ?: ""
        }

    suspend fun setRecipeName(recipeName : String){
        context.datastore.edit {
            it[key] = recipeName
        }
    }

}