package com.example.sharerecipy.api.request

import android.content.Context
import androidx.lifecycle.LiveData
import com.firebase.ui.auth.data.model.User

interface AccountService {
    fun login( // 로그인
        email: String, password: String, context: Context,  openAndPopUp: (String, String) -> Unit)
    fun createAccount( // 회원가입
        email: String, name: String, password: String, confirmPw: String, context: Context, openAndPopUp: (String, String) -> Unit)
    fun deleteAccount(context: Context, openAndPopUp: (String, String) -> Unit) // 계정 삭제
    fun logout(context: Context, openAndPopUp: (String, String) -> Unit) // 로그아웃
    fun autoLogin(openAndPopUp: (String, String) -> Unit) // 자동 로그인
    fun editProfile(context: Context, name: String, password: String, openAndPopUp: (String, String) -> Unit) // 정보 수정
}