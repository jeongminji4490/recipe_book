package com.example.sharerecipy.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
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