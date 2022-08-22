package com.example.sharerecipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharerecipy.screens.home.HomeScreen
import com.example.sharerecipy.screens.login.LoginScreen
import com.example.sharerecipy.screens.recipe.*
import com.example.sharerecipy.screens.profile.EditProfileScreen
import com.example.sharerecipy.screens.signup.SignUpScreen
import com.example.sharerecipy.screens.wish.WishListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val appState = MainAppState(navController)
            MaterialTheme {
                NavigationComponent(
                    navController,
                    appState
                )
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        appState: MainAppState
    ) {
        NavHost(
            navController = navController,
            startDestination = LOGIN_SCREEN
        ){
            composable(LOGIN_SCREEN) {
                LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(SIGNUP_SCREEN) {
                SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(HOME_SCREEN) {
                HomeScreen(
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(WISH_LIST_SCREEN){
                WishListScreen(
                    openScreen = { route -> appState.navigate(route) },
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(EDIT_PROFILE_SCREEN){
                EditProfileScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(RECIPE_LIST_SCREEN) {
                RecipeListScreen(
                    openScreen = { route -> appState.navigate(route) },
                    openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                )
            }
            composable(RECIPE_SCREEN){
                RecipeScreen(
                    popUpScreen = { appState.popUp() }
                )
            }
        }
    }
}