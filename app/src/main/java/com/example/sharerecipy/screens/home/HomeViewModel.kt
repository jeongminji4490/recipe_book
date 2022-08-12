package com.example.sharerecipy.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharerecipy.api.request.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService
): ViewModel() {

    // 로그아웃
    fun logout(context: Context, openAndPopUp: (String, String) -> Unit) {
        accountService.signOut(context, openAndPopUp)
    }

    // 회원 탈퇴
    fun accountWithdrawal(context: Context, openAndPopUp: (String, String) -> Unit) {
        accountService.deleteAccount(context, openAndPopUp)
    }
}