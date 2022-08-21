package com.example.sharerecipy.screens.wish

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.example.sharerecipy.common.composable.ChipComposable
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
    val wishKeyList = wishList.keys.toList()
    val scrollState = rememberScrollState()
    var typeValue by rememberSaveable { mutableStateOf("All") }

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
                .background(Navy)
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChipComposable(AppText.all) { typeValue = "All" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.rice) { typeValue = "밥" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.soup) { typeValue = "국&찌개"  }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.side) { typeValue = "반찬" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.ilpoom) { typeValue = "일품" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.dessert) { typeValue = "후식" }
                Spacer(Modifier.width(5.dp))
            }
            wishKeyList.let {
                LazyColumn {
                    items(it) { name ->
                        val ingredients = wishList[name]?.get(0)
                        val type = wishList[name]?.get(1)
                        if (typeValue == "All") {
                            if (ingredients != null && type != null)
                                WishListCard(name, ingredients, type, openScreen)
                        }else {
                            if (type == typeValue){
                                if (ingredients != null)
                                    WishListCard(name, ingredients, type, openScreen)
                            }
                        }
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
    type: String,
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
        backgroundColor = Navy,
        border = BorderStroke(1.dp, Beige),
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
                        tint = Yellow
                    )
                }
                IconButton(
                    onClick = { viewModel.setRecipeName(name, openScreen) },
                ) {
                    Icon(
                        Icons.Filled.MenuBook,
                        contentDescription = "",
                        tint = VividOrange
                    )
                }
                IconButton(
                    onClick = {
                        pickState.value = !pickState.value
                        if (pickState.value){
                            viewModel.addWishRecipe(name, ingredients, type)
                        }else {
                            viewModel.deleteWishRecipe(name)
                        } },
                ) {
                    Icon(
                        Icons.Filled.Bookmark,
                        contentDescription = "",
                        tint = if (!pickState.value) LightGray else Yellow
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
                color = Beige,
                fontSize = 15.sp,
                fontFamily = LightFont
            )
        }

    }
}

const val TAG = "WishListScreen"