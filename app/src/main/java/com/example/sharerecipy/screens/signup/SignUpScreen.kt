package com.example.sharerecipy.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sharerecipy.LOGIN_SCREEN
import com.example.sharerecipy.R.*
import com.example.sharerecipy.SIGNUP_SCREEN
import com.example.sharerecipy.common.composable.BasicButton
import com.example.sharerecipy.common.composable.EmailField
import com.example.sharerecipy.common.composable.NameField
import com.example.sharerecipy.common.composable.PasswordField
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.R.color as AppColor

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    openAndPopUp: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var confirmPw by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(AppColor.lightOrange))
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
        EmailField(AppText.email, email) { email = it }
        NameField(AppText.name, name) { name = it }
        PasswordField( AppText.password, pw) { pw = it }
        PasswordField( AppText.confirm_password, confirmPw) { confirmPw = it }
        Spacer(modifier = Modifier.height(30.dp))
        BasicButton(
            AppText.sign_up,
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            viewModel.createAccount(
                email, name, pw, confirmPw, context, openAndPopUp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        BasicButton(
            AppText.cancel,
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) { openAndPopUp(LOGIN_SCREEN, SIGNUP_SCREEN) }
    }
}