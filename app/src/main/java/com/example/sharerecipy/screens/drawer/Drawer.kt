package com.example.sharerecipy.screens.drawer

import androidx.compose.foundation.Image
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.EDIT_PROFILE_SCREEN
import com.example.sharerecipy.LOGIN_SCREEN
import com.example.sharerecipy.R
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.Dialog
import com.example.sharerecipy.common.theme.Navy
import com.example.sharerecipy.common.theme.Red
import com.example.sharerecipy.common.theme.White
import com.example.sharerecipy.screens.home.HomeViewModel

sealed class DrawerScreens(val title: String, val route: String) {
    object WishList : DrawerScreens("Wish List", WISH_LIST_SCREEN)
    object EditProfile : DrawerScreens("Edit Profile", EDIT_PROFILE_SCREEN)
    object Logout : DrawerScreens("Logout", LOGIN_SCREEN)
    object Withdrawal : DrawerScreens("WithDrawal Account", LOGIN_SCREEN)
}

private val screens = listOf(
    DrawerScreens.WishList,
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
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.clickable {
                    when (screen){
                        DrawerScreens.WishList -> onDestinationClicked(screen.route)
                        DrawerScreens.EditProfile -> onDestinationClicked(screen.route)
                        DrawerScreens.Logout -> viewModel.logout(context, openAndPopUp)
                        DrawerScreens.Withdrawal -> withdrawalDialog.value = true
                    }
                }
            )
        }
        if (withdrawalDialog.value) {
            Dialog(
                R.string.withdrawal,
                stringResource(R.string.withdrawal_dialog),
                Red,
                Icons.Filled.ExitToApp,
                { viewModel.accountWithdrawal(context, openAndPopUp) },
                { withdrawalDialog.value = false }
            )
        }
    }
}

