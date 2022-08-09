package com.example.sharerecipy.model.service

import android.content.Context

interface AccountService {
    fun authenticate(
        email: String, password: String, context: Context,  openAndPopUp: (String, String) -> Unit)
    fun createAccount(
        email: String, name: String, password: String, confirmPw: String, context: Context, openAndPopUp: (String, String) -> Unit)
    fun deleteAccount(context: Context, openAndPopUp: (String, String) -> Unit)
    fun signOut(context: Context, openAndPopUp: (String, String) -> Unit)
    fun autoLogin(openAndPopUp: (String, String) -> Unit)
}