package com.example.sharerecipy.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
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
import com.example.sharerecipy.R.*
import com.example.sharerecipy.R.string as AppText

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
    openAndPopUp: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var confirmPw by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(color.lightOrange))
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
        OutlinedTextField( // 이메일 입력
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(AppText.email), color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = colorResource(color.crimsonRed),
                unfocusedBorderColor = Color.White
            )
        )
        OutlinedTextField( // 비밀번호 입력
            modifier = Modifier.fillMaxWidth(),
            value = pw,
            onValueChange = { pw = it },
            label = { Text(stringResource(AppText.password), color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = colorResource(color.crimsonRed),
                unfocusedBorderColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        OutlinedTextField( // 비밀번호 확인
            modifier = Modifier.fillMaxWidth(),
            value = confirmPw,
            onValueChange = { confirmPw = it },
            label = { Text(stringResource(AppText.confirm_password), color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = colorResource(color.crimsonRed),
                unfocusedBorderColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { viewModel.createAccount(email, pw, confirmPw, context, openAndPopUp) }, // 가입절차 실행
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(color.lightOrange),
                contentColor =  Color.White
            )
        ) {
            Text(
                text = stringResource(AppText.sign_up),
                fontSize = 17.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate("login") }, // 로그인 화면으로 이동
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(color.lightOrange),
                contentColor = Color.White)
        ) {
            Text(
                stringResource(AppText.cancel),
                fontSize = 17.sp
            )
        }
    }
}