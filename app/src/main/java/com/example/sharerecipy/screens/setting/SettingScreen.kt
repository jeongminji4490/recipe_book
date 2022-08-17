package com.example.sharerecipy.screens.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.EDIT_PROFILE_SCREEN
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.SETTING_SCREEN
import com.example.sharerecipy.common.composable.*
import com.example.sharerecipy.common.theme.Red
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.R.color as AppColor

@Composable
fun SettingScreen(
    openAndPopUp: (String, String) -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(AppText.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, SETTING_SCREEN)
            }
        },
        backgroundColor = colorResource(AppColor.lightOrange),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { MainContent(openAndPopUp) }
        })
}

@Composable
fun MainContent(openAndPopUp: (String, String) -> Unit){
    val viewModel : SettingViewModel = hiltViewModel()
    val context = LocalContext.current
    val withdrawalDialog = remember { mutableStateOf(false) }

    Column {
        BasicButton( // 정보수정 버튼
            AppText.modify_info,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(50.dp)
        ) { openAndPopUp(EDIT_PROFILE_SCREEN, SETTING_SCREEN) }
        BasicButton( // 로그아웃 버튼
            AppText.logout,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(50.dp)
        ) { viewModel.logout(context, openAndPopUp) }
        Spacer(modifier = Modifier.height(10.dp))
        BasicButton( // 회원탈퇴 버튼
            AppText.withdrawal,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(50.dp)
        ) { withdrawalDialog.value = true }
        Spacer(modifier = Modifier.height(10.dp))
        if (withdrawalDialog.value) {
            Dialog(
                AppText.withdrawal,
                stringResource(AppText.withdrawal_dialog),
                Red,
                Icons.Filled.ExitToApp,
                { viewModel.accountWithdrawal(context, openAndPopUp) },
                { withdrawalDialog.value = false }
            )
        }
    }
}