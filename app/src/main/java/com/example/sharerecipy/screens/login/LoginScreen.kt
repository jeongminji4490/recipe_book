package com.example.sharerecipy.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharerecipy.App
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.*
import com.example.sharerecipy.R.string as AppText

// 상태 호이스팅 패턴으로 구현할 시 LoginScreen에 전달해야할 인자가 많아짐(이건 데이터클래스로 해결할 수 있을 것 같긴함)
// 하지민 LoginScreen은 여기서만 쓰이는 컴포저블인데 상태 호이스팅 패턴으로 구성할 필요가 있을까?

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    openAndPopUp: (String, String) -> Unit
){
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var progressState by remember { mutableStateOf(0) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(colorResource(color.lightOrange))
        .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_1),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.app_name_2),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        GlideImage(
            imageModel = drawable.vegetables,
            modifier = Modifier
                .height(150.dp)
                .width(210.dp)
        )
        OutlinedTextField( // id 입력을 위한 TextField
            modifier = Modifier
                .fillMaxWidth(),
            value = id,
            onValueChange = { id = it },
            label = { Text(stringResource(AppText.email), color = Color.White ) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = colorResource(color.mint),
                unfocusedBorderColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color.White
                )
            }
        )
        OutlinedTextField( // 비밀번호 입력을 위한 TextField
            modifier = Modifier
                .fillMaxWidth(),
            value = pw,
            onValueChange = { pw = it },
            label = { Text(stringResource(AppText.password), color = Color.White ) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = colorResource(color.mint),
                unfocusedBorderColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                    tint = Color.White
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button( onClick = { viewModel.login(id, pw, context, openAndPopUp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(color.white),
                contentColor = colorResource(color.lightOrange)
            )
        ){
            Text(
                stringResource(AppText.sign_in),
                fontSize = 17.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate("sign_up") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(color.lightOrange),
                contentColor = Color.White)
        ) {
            Text(
                stringResource(AppText.create_account),
                fontSize = 17.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (progressState == 1){ // 1이 되면 진행바 실행
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = colorResource(color.mint),
                strokeWidth = Dp(value = 4F)
            )
        }
    }
}
