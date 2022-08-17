package com.example.sharerecipy.api.request

import android.content.Context
import androidx.lifecycle.LiveData
import com.firebase.ui.auth.data.model.User

interface AccountService {
    fun authenticate(
        email: String, password: String, context: Context,  openAndPopUp: (String, String) -> Unit)
    fun createAccount(
        email: String, name: String, password: String, confirmPw: String, context: Context, openAndPopUp: (String, String) -> Unit)
    fun deleteAccount(context: Context, openAndPopUp: (String, String) -> Unit)
    fun signOut(context: Context, openAndPopUp: (String, String) -> Unit)
    fun autoLogin(openAndPopUp: (String, String) -> Unit)
    fun editProfile(context: Context, name: String, password: String, openAndPopUp: (String, String) -> Unit)
}