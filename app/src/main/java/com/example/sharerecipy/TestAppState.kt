package com.example.sharerecipy

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

// 추후에 클래스명 변경해야할듯 ??
// @Stable : When applied to a function or a property, the Stable annotation indicates that the function will return the same result if the same parameters are passed in.
// This is only meaningful if the parameters and results are themselves Stable, Immutable, or primitive.
@Stable
class TestAppState(
    val navController: NavHostController
) {
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}