package com.example.sharerecipy.model.service

import android.content.Context

interface AccountService {
    fun authenticate(email: String, password: String, context: Context,  openAndPopUp: (String, String) -> Unit)
    fun createAccount(email: String, password: String, confirmPw: String, context: Context, openAndPopUp: (String, String) -> Unit)
    fun deleteAccount(onResult: (Throwable?) -> Unit)
    fun signOut()

//    fun hasUser(): Boolean
//    fun isAnonymousUser(): Boolean
//    fun getUserId(): String
//    fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit)
//    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
//    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
}