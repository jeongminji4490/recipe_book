package com.example.sharerecipy.screens.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sharerecipy.api.request.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel  @Inject constructor(
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