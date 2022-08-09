package com.example.sharerecipy.screens.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sharerecipy.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    // 계정 생성
    fun createAccount(
        email: String,
        name: String,
        password: String,
        confirmPw: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit) {
        accountService.createAccount(email, name, password, confirmPw, context, openAndPopUp)
    }

}