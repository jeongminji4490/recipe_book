package com.example.sharerecipy.screens.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R
import com.example.sharerecipy.SETTING_SCREEN
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.R.color as AppColor
import com.example.sharerecipy.common.composable.BasicButton
import com.example.sharerecipy.common.composable.ColorButton
import com.example.sharerecipy.common.composable.DialogConfirmButton
import com.example.sharerecipy.common.composable.Toolbar

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    openAndPopUp: (String, String) -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(AppText.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, SETTING_SCREEN)
            }
        },
        backgroundColor = colorResource(AppColor.lightOrange),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { MainContent(viewModel, openAndPopUp) }
        })
}

@Composable
fun MainContent(
    viewModel: SettingViewModel,
    openAndPopUp: (String, String) -> Unit
){

    val context = LocalContext.current
    val withdrawalDialog = remember { mutableStateOf(false) }

    Column {
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
        if (withdrawalDialog.value){
            AlertDialog(
                backgroundColor = Color.White,
                onDismissRequest = { withdrawalDialog.value = false },
                title = {
                    Text(
                        text = stringResource(AppText.withdrawal),
                        color = Color.Red)
                },
                text = {
                    Text(
                        text = stringResource(AppText.withdrawal_dialog),
                        color = Color.Black)
                },
                confirmButton = {
                    DialogConfirmButton(
                        AppText.OK,
                        action = { viewModel.accountWithdrawal(context, openAndPopUp) },
                        R.color.red,
                        R.color.white
                    )
                }
            )
        }
    }
}