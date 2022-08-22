package com.example.recipebook.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.LOGIN_SCREEN
import com.example.recipebook.R.*
import com.example.recipebook.SIGNUP_SCREEN
import com.example.recipebook.common.composable.*
import com.example.recipebook.common.theme.*
import com.example.recipebook.R.string as AppText

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    openAndPopUp: (String, String) -> Unit
){
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }
    var confirmPw by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Beige)
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(AppText.sign_up),
            textAlign = TextAlign.Left,
            color = Navy,
            fontSize = 40.sp,
            fontFamily = BoldFont
        )

        Spacer(modifier = Modifier.height(60.dp))

        EmailField(AppText.email, email) { email = it }

        NameField(AppText.name, name) { name = it }

        PasswordField( AppText.password, pw) { pw = it }

        PasswordField( AppText.confirm_password, confirmPw) { confirmPw = it}

        Spacer(modifier = Modifier.height(30.dp))

        BasicButton(
            AppText.sign_up,
            Modifier
                .fillMaxWidth()
                .height(60.dp),
            Beige,
            Navy
        ) { viewModel.createAccount(email, name, pw, confirmPw, context, openAndPopUp) }

        Spacer(modifier = Modifier.height(10.dp))

        BasicButton(
            AppText.CANCEL,
            Modifier
                .fillMaxWidth()
                .height(60.dp),
            Beige,
            Navy
        ) { openAndPopUp(LOGIN_SCREEN, SIGNUP_SCREEN) }
    }
}