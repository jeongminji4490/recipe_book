package com.example.recipebook.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.LOGIN_SCREEN
import com.skydoves.landscapist.glide.GlideImage
import com.example.recipebook.R.*
import com.example.recipebook.SIGNUP_SCREEN
import com.example.recipebook.common.composable.*
import com.example.recipebook.common.theme.*
import com.example.recipebook.R.string as AppText

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    openAndPopUp: (String, String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Beige)
        .padding(20.dp),
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

        EmailField( AppText.email, email) { email = it }

        PasswordField( AppText.password, pw) { pw = it }

        Spacer(modifier = Modifier.height(10.dp))

        BasicButton(
            AppText.sign_in,
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            Navy,
            White
        ) { viewModel.login(email, pw, context, openAndPopUp) }

        Spacer(modifier = Modifier.height(10.dp))

        BasicButton(
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

