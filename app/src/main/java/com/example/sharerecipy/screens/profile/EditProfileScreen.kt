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
    val currentUser = Firebase.auth.currentUser // 로그인한 사용자
    var email by rememberSaveable { mutableStateOf("") } // 이메일
    var name by rememberSaveable { mutableStateOf("") } // 닉네임
    var password by rememberSaveable { mutableStateOf("") } // 비밀번호
    var confirmPw by rememberSaveable { mutableStateOf("") } // 비밀번호 확인

    email = currentUser?.email.toString()
    name = currentUser?.displayName.toString()

    Scaffold(
        topBar = {
            Toolbar(AppText.edit_profile, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, EDIT_PROFILE_SCREEN)
            }
        },
        //backgroundColor = colorResource(R.color.white),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Beige)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditProfileContent(email, name, password, confirmPw, {name=it}, {password=it}, {confirmPw=it}, openAndPopUp)
            }
        })
}

@Composable
fun EditProfileContent(
    email: String,
    name: String,
    password: String,
    confirmPw: String,
    onNameChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onConfirmPwChange: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel : ProfileViewModel = hiltViewModel()
    val context = LocalContext.current

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

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            textAlign = TextAlign.Left,
            color = Navy,
            fontSize = 22.sp,
            fontFamily = BoldFont
        )

        Spacer(modifier = Modifier.height(10.dp))

        NameField(
            AppText.name,
            name,
            onNameChange)
        PasswordField(
            AppText.password,
            password,
            onPwChange)
        PasswordField(
            AppText.confirm_password,
            confirmPw,
            onConfirmPwChange)

        Spacer(modifier = Modifier.height(20.dp))

        ColorButton(
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