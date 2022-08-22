package com.example.sharerecipy.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.EDIT_PROFILE_SCREEN
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.common.composable.*
import com.example.sharerecipy.common.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun EditProfileScreen(
    openAndPopUp: (String, String) -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(AppText.edit_profile, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, EDIT_PROFILE_SCREEN)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Beige)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditProfileContent(openAndPopUp)
            }
        })
}

@Composable
fun EditProfileContent(
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel : ProfileViewModel = hiltViewModel()
    val context = LocalContext.current

    val currentUser = Firebase.auth.currentUser
    val email = currentUser?.email.toString() // 이메일 (변경 불가)
    var name by rememberSaveable { mutableStateOf(currentUser?.displayName.toString()) } // 닉네임
    var password by rememberSaveable { mutableStateOf("") } // 비밀번호
    var confirmPw by rememberSaveable { mutableStateOf("") } // 비밀번호 확인

    Column(
        Modifier.padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.edit_profile),
            textAlign = TextAlign.Left,
            color = Black,
            fontSize = 40.sp,
            fontFamily = BoldFont
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text( // 이메일
            modifier = Modifier.fillMaxWidth(),
            text = email,
            textAlign = TextAlign.Left,
            color = Navy,
            fontSize = 22.sp,
            fontFamily = BoldFont
        )

        Spacer(modifier = Modifier.height(10.dp))

        NameField(AppText.name, name) { name = it }
        PasswordField(AppText.password, password) { password = it }
        PasswordField(AppText.confirm_password, confirmPw) { confirmPw = it }

        Spacer(modifier = Modifier.height(20.dp))

        BasicButton(
            AppText.OK,
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            Beige,
            Navy
        ) {
            if (name != "" && password != "" && confirmPw != ""){
                if (password != confirmPw)
                    Toast.makeText(context, AppText.password_match_error, Toast.LENGTH_SHORT).show()
                else
                    viewModel.editProfile(context, name, confirmPw, openAndPopUp)
            }else {
                Toast.makeText(context, AppText.empty_password, Toast.LENGTH_SHORT).show()
            }
        }
    }
}