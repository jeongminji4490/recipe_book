package com.example.sharerecipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharerecipy.screens.home.HomeScreen
import com.example.sharerecipy.screens.home.HomeViewModel
import com.example.sharerecipy.screens.login.LoginScreen
import com.example.sharerecipy.screens.login.LoginViewModel
import com.example.sharerecipy.screens.recipe.RecipeDetailScreen
import com.example.sharerecipy.screens.recipe.RecipeScreen
import com.example.sharerecipy.screens.recipe.ViewModel
import com.example.sharerecipy.screens.setting.SettingScreen
import com.example.sharerecipy.screens.setting.SettingViewModel
import com.example.sharerecipy.screens.signup.SignUpScreen
import com.example.sharerecipy.screens.signup.SignUpViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.NoSuchElementException
import kotlin.math.log

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val recipeViewModel = hiltViewModel<ViewModel>()
            val settingViewModel = hiltViewModel<SettingViewModel>()
            val appState = TestAppState(navController)
            MaterialTheme {
                NavigationComponent(
                    navController,
                    loginViewModel,
                    signUpViewModel,
                    homeViewModel,
                    settingViewModel,
                    recipeViewModel,
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
        homeViewModel: HomeViewModel,
        settingViewModel: SettingViewModel,
        recipeViewModel: ViewModel,
        appState: TestAppState
    ) {
        NavHost(
            navController = navController,
            startDestination = LOGIN_SCREEN
        ){
            composable(LOGIN_SCREEN) {
                LoginScreen(
                    loginViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(SIGNUP_SCREEN) {
                SignUpScreen(
                    signUpViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(HOME_SCREEN) {
                HomeScreen(
                    homeViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(SETTING_SCREEN) {
                SettingScreen(
                    settingViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(RECIPE_SCREEN) {
                RecipeScreen(
                    recipeViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(RECIPE_DETAIL_SCREEN){
                RecipeDetailScreen(
                    recipeViewModel,
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
        }
    }
}