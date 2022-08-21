package com.example.sharerecipy.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.LOGIN_SCREEN
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.*
import com.example.sharerecipy.SIGNUP_SCREEN
import com.example.sharerecipy.common.composable.*
import com.example.sharerecipy.common.theme.*
import com.example.sharerecipy.R.string as AppText

@Composable
fun LoginScreen(openAndPopUp: (String, String) -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }
    LoginContent(email, pw, {email=it}, {pw=it}, openAndPopUp)
}

// 자동 로그인 추가 ??
@Composable
fun LoginContent(
    email: String,
    pw: String,
    onEmailChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
){
    val viewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Beige)
        .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_1),
            textAlign = TextAlign.Center,
            color = Navy,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_2),
            textAlign = TextAlign.Center,
            color = Navy,
            fontSize = 40.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold)

        GlideImage(
            imageModel = drawable.vegetables,
            modifier = Modifier
                .height(150.dp)
                .width(210.dp))

        EmailField( AppText.email, email, onEmailChange)

        PasswordField( AppText.password, pw, onPwChange)

        Spacer(modifier = Modifier.height(10.dp))

        ColorButton( // 로그인 버튼
            AppText.sign_in,
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            Navy,
            White
        ) { viewModel.login(email, pw, context, openAndPopUp) }

        Spacer(modifier = Modifier.height(10.dp))

        ColorButton( // 로그인 버튼
            AppText.sign_up,
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            Beige,
            Navy
        ) { openAndPopUp(SIGNUP_SCREEN, LOGIN_SCREEN) }


        Spacer(modifier = Modifier.height(10.dp))
    }
}

