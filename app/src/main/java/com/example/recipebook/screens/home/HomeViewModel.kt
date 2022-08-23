package com.example.recipebook.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.*
import com.example.recipebook.api.request.AccountService
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

    // 클릭한 레시피 종류에 해당하는 레시피 목록 화면으로 이동
    fun setRecipeType(recipeType: String, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            dataStore.setRecipeType(recipeType)
            openAndPopUp(RECIPE_LIST_SCREEN, HOME_SCREEN)
        }
    }
}