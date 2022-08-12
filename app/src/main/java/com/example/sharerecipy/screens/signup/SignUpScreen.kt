package com.example.sharerecipy.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.LOGIN_SCREEN
import com.example.sharerecipy.R.*
import com.example.sharerecipy.SIGNUP_SCREEN
import com.example.sharerecipy.common.composable.BasicButton
import com.example.sharerecipy.common.composable.EmailField
import com.example.sharerecipy.common.composable.NameField
import com.example.sharerecipy.common.composable.PasswordField
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.R.string as AppText

@Composable
fun SignUpScreen(openAndPopUp: (String, String) -> Unit){
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }
    var confirmPw by rememberSaveable { mutableStateOf("") }
    SignUpContent(email, name, pw, confirmPw, {email=it}, {name=it}, {pw=it}, {confirmPw=it}, openAndPopUp)
}

@Composable
fun SignUpContent(
   email: String,
   name: String,
   pw: String,
   confirmPw: String,
   onEmailChange: (String) -> Unit,
   onNameChange: (String) -> Unit,
   onPwChange: (String) -> Unit,
   onConfirmPwChange: (String) -> Unit,
   openAndPopUp: (String, String) -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.sign_up),
            textAlign = TextAlign.Left,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(60.dp))
        EmailField(AppText.email, email, onEmailChange) // 이메일 입력
        NameField(AppText.name, name, onNameChange) // 이름 입력
        PasswordField( AppText.password, pw, onPwChange) // 패스워드 입력
        PasswordField( AppText.confirm_password, confirmPw, onConfirmPwChange) // 패스워드 확인
        Spacer(modifier = Modifier.height(30.dp))
        BasicButton( // 계정 생성
            AppText.sign_up,
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) { viewModel.createAccount(email, name, pw, confirmPw, context, openAndPopUp) }
        Spacer(modifier = Modifier.height(10.dp))
        BasicButton( // 뒤로가기
            AppText.cancel,
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) { openAndPopUp(LOGIN_SCREEN, SIGNUP_SCREEN) }
    }
}