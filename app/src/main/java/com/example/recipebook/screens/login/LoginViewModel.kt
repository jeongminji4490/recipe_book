package com.example.recipebook.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.recipebook.api.request.AccountService
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
        accountService.login(email, password, context, openAndPopUp)
    }
}