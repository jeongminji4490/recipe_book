package com.example.sharerecipy.screens.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sharerecipy.common.ext.isValidEmail
import com.example.sharerecipy.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.sharerecipy.R.string as AppText

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

    // 로그아웃
    fun logout() {
        accountService.signOut()
    }
}