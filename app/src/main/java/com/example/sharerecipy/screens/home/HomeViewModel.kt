package com.example.sharerecipy.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharerecipy.DataStore
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.RECIPE_DETAIL_SCREEN
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.api.request.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val dataStore: DataStore
): ViewModel() {

    // 로그아웃
    fun logout(context: Context, openAndPopUp: (String, String) -> Unit) {
        accountService.logout(context, openAndPopUp)
    }

    // 회원 탈퇴
    fun accountWithdrawal(context: Context, openAndPopUp: (String, String) -> Unit) {
        accountService.deleteAccount(context, openAndPopUp)
    }

    fun setRecipeType(recipeType: String, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            dataStore.setRecipeType(recipeType)
            openAndPopUp(RECIPE_SCREEN, HOME_SCREEN)
        }
    }
}