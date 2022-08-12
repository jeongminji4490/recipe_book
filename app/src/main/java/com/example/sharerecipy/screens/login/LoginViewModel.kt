package com.example.sharerecipy.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sharerecipy.api.request.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    // 로그인
    fun login(
        email: String,
        password: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        accountService.authenticate(email, password, context, openAndPopUp)
    }

    // 자동로그인
    fun autoLogin(
        openAndPopUp: (String, String) -> Unit
    ){
        accountService.autoLogin(openAndPopUp)
    }
}