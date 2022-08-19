package com.example.sharerecipy.screens.wish

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.Dialog
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.*
import com.example.sharerecipy.screens.recipe.RecipeCard
import com.example.sharerecipy.screens.recipe.RecipeViewModel

@Composable
fun WishListScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel : RecipeViewModel = hiltViewModel()
    val wishList = viewModel.wishList
    val w = wishList.keys.toList()
    val n = wishList.values.toList()

    LaunchedEffect(true){
        wishList.clear()
        viewModel.getWishList()
    }

    Scaffold(
        topBar = {
            Toolbar(AppText.wish_list_eng, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, WISH_LIST_SCREEN) }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(White)
        ) {
            w.let {
                LazyColumn {
                    items(it) { name ->
                        val ingredients = wishList[name]
                        if (ingredients != null)
                            WishListCard(name, ingredients, openScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun WishListCard(
    name: String,
    ingredients: String,
    openScreen: (String) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val ingredientDialog = remember { mutableStateOf(false) }
    val pickState = remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp),
                //verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { ingredientDialog.value = true },
                ) {
                    Icon(
                        Icons.Filled.Kitchen,
                        contentDescription = "",
                        tint = CrimsonRed
                    )
                }
                IconButton(
                    onClick = { viewModel.setRecipeName(name, openScreen) },
                ) {
                    Icon(
                        Icons.Filled.MenuBook,
                        contentDescription = "",
                        tint = CrimsonRed
                    )
                }
                IconButton(
                    onClick = {
                        pickState.value = !pickState.value
                        if (pickState.value){
                            viewModel.addWishRecipe(name, ingredients)
                        }else {
                            viewModel.deleteWishRecipe(name, ingredients)
                        } },
                ) {
                    Icon(
                        Icons.Filled.Bookmark,
                        contentDescription = "",
                        tint = if (!pickState.value) LightGray else CrimsonRed
                    )
                }
                if (ingredientDialog.value) {
                    Dialog(
                        AppText.ingredients_dialog,
                        ingredients,
                        Black,
                        Icons.Filled.Restaurant,
                        { ingredientDialog.value = false },
                        { ingredientDialog.value = false }
                    )
                }
            }
            Text( // 레시피명
                text = name,
                textAlign = TextAlign.Left,
                color = Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
//            Icon(
//                Icons.Filled.OutdoorGrill,
//                contentDescription = "",
//                tint = Black,
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(5.dp)
//            )
//            Icon(
//                Icons.Filled.Favorite,
//                contentDescription = "",
//                tint = Red,
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(5.dp)
//            )
        }

    }
}

const val TAG = "WishListScreen"