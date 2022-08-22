package com.example.recipebook.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.EDIT_PROFILE_SCREEN
import com.example.recipebook.LOGIN_SCREEN
import com.example.recipebook.common.composable.Dialog
import com.example.recipebook.common.theme.*
import com.example.recipebook.screens.home.HomeViewModel
import com.example.recipebook.R.string as AppText

sealed class DrawerScreens(val title: String, val route: String) {
    object EditProfile : DrawerScreens("내정보 수정", EDIT_PROFILE_SCREEN)
    object Logout : DrawerScreens("로그아웃", LOGIN_SCREEN)
    object Withdrawal : DrawerScreens("회원 탈퇴", LOGIN_SCREEN)
}

private val screens = listOf(
    DrawerScreens.EditProfile,
    DrawerScreens.Logout,
    DrawerScreens.Withdrawal
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onDestinationClicked: (route: String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val context = LocalContext.current
    val withdrawalDialog = remember { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxSize()
            .background(Navy)
            .padding(start = 24.dp, top = 48.dp)
    ) {
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                color = White,
                fontSize = 20.sp,
                fontFamily = LightFont,
                modifier = Modifier.clickable {
                    when (screen){
                        DrawerScreens.EditProfile -> onDestinationClicked(screen.route)
                        DrawerScreens.Logout -> viewModel.logout(context, openAndPopUp)
                        DrawerScreens.Withdrawal -> withdrawalDialog.value = true
                    }
                }
            )
        }
        if (withdrawalDialog.value) {
            Dialog(
                AppText.withdrawal,
                stringResource(AppText.withdrawal_dialog),
                Navy,
                Icons.Filled.ExitToApp,
                { viewModel.accountWithdrawal(context, openAndPopUp) },
                { withdrawalDialog.value = false }
            )
        }
    }
}

