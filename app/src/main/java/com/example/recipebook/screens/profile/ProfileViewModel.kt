package com.example.recipebook.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.recipebook.api.request.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val accountService: AccountService
): ViewModel() {

    fun editProfile(
        context: Context,
        name: String,
        password: String,
        openAndPopUp: (String, String) -> Unit) {
        accountService.editProfile(context, name, password, openAndPopUp)
    }
}