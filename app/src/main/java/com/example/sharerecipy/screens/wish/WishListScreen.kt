package com.example.sharerecipy.screens.wish

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.LightGray
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.common.theme.Red
import com.example.sharerecipy.screens.recipe.RecipeCard
import com.example.sharerecipy.screens.recipe.RecipeViewModel

@Composable
fun WishListScreen(
    openAndPopUp: (String, String) -> Unit
) {

    val viewModel : RecipeViewModel = hiltViewModel()
    val wishList = viewModel.wishList

    LaunchedEffect(true){
        viewModel.getWishList()
    }

    Scaffold(
        topBar = {
            Toolbar(R.string.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, WISH_LIST_SCREEN)
            } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(LightOrange)
        ) {
            wishList.let {
                LazyColumn {
                    items(it) { name ->
                        WishListCard(name, openAndPopUp)
                    }
                }
            }
        }
    }
}

@Composable
fun WishListCard(
    name: String,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()

    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp,
    ) {
        Row() {
            Text( // 레시피명
                text = name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "",
                tint = Red
            )
        }

    }
}

const val TAG = "WishListScreen"