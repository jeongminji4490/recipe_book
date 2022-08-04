package com.example.sharerecipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharerecipy.screens.home.HomeScreen
import com.example.sharerecipy.screens.login.LoginScreen
import com.example.sharerecipy.screens.login.LoginViewModel
import com.example.sharerecipy.screens.signup.SignUpScreen
import com.example.sharerecipy.screens.signup.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            val appState = TestAppState(navController)
            MaterialTheme {
                NavigationComponent(
                    navController,
                    loginViewModel,
                    signUpViewModel,
                    appState
                )
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        loginViewModel: LoginViewModel,
        signUpViewModel: SignUpViewModel,
        appState: TestAppState
    ) {
        NavHost(
            navController = navController,
            startDestination = "login"
        ){
            composable("login") {
                LoginScreen(
                    navController,
                    loginViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable("sign_up") {
                SignUpScreen(
                    navController,
                    signUpViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable("home") {
                HomeScreen(navController)
            }
        }
    }
}