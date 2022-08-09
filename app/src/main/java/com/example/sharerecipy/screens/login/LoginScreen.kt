package com.example.sharerecipy.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.example.sharerecipy.LOGIN_SCREEN
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.*
import com.example.sharerecipy.SIGNUP_SCREEN
import com.example.sharerecipy.common.composable.*
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.R.color as AppColor

// 상태 호이스팅 패턴으로 구현할 시 전달해야할 인자가 많아짐(이건 데이터클래스로 해결할 수 있을 것 같긴함)
// 하지만 LoginScreen -> 여기서만 쓰이는 컴포저블인데 상태 호이스팅 패턴으로 구성할 필요가 있을까?

// 자동 로그인 추가 ??
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    openAndPopUp: (String, String) -> Unit
){

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(colorResource(AppColor.lightOrange))
        .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_1),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_2),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        GlideImage(
            imageModel = drawable.vegetables,
            modifier = Modifier
                .height(150.dp)
                .width(210.dp)
        )
        EmailField( AppText.email, email) { email = it }
        PasswordField( AppText.password, pw) { pw = it }
        Spacer(modifier = Modifier.height(10.dp))
        ColorButton( // 로그인 버튼
            AppText.sign_in,
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            AppColor.white,
            AppColor.lightOrange
        ) {
            viewModel.login(email, pw, context, openAndPopUp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        BasicButton( // 회원가입 버튼
            AppText.sign_up,
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) { openAndPopUp(SIGNUP_SCREEN, LOGIN_SCREEN) }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
