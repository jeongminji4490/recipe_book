package com.example.sharerecipy.screens.wish

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.ChipComposable
import com.example.sharerecipy.common.composable.Dialog
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.*
import com.example.sharerecipy.screens.recipe.RecipeViewModel

@Composable
fun WishListScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel : RecipeViewModel = hiltViewModel()
    val wishList = viewModel.wishList
    val wishKeyList = wishList.keys.toList() // 찜 목록의 key(레시피명) 리스트
    val scrollState = rememberScrollState()
    var categoryValue by rememberSaveable { mutableStateOf("All") }

    LaunchedEffect(true){
        wishList.clear()
        viewModel.getWishList()
    }

    Scaffold(
        topBar = {
            Toolbar(AppText.wish_list, Icons.Filled.ArrowBack) {
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
                categories.forEach { category ->
                    ChipComposable(category) { categoryValue = category }
                    Spacer(Modifier.width(5.dp))
                }
            }
            wishKeyList.let { // 찜 목록 조회
                LazyColumn {
                    items(it) { name ->
                        val ingredients = wishList[name]?.get(0) // 재료
                        val category = wishList[name]?.get(1) // 카테고리
                        if (categoryValue == "All") { // 모두 조회
                            if (ingredients != null && category != null)
                                WishListCard(name, ingredients, category, openScreen)
                        }else { // 카테고리별 조회
                            if (category == categoryValue){
                                if (ingredients != null)
                                    WishListCard(name, ingredients, category, openScreen)
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
            ) {
                IconButton( // 재료
                    onClick = { ingredientDialog.value = true },
                ) {
                    Icon(
                        Icons.Filled.Kitchen,
                        contentDescription = "",
                        tint = Yellow
                    )
                }
                IconButton( // 레시피
                    onClick = { viewModel.setRecipeName(name, openScreen) },
                ) {
                    Icon(
                        Icons.Filled.MenuBook,
                        contentDescription = "",
                        tint = VividOrange
                    )
                }
                IconButton( // 북마크
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
                if (ingredientDialog.value) { // 재료 다이얼로그
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

val categories = listOf("All", "밥", "국&찌개", "반찬", "일품", "후식")

const val TAG = "WishListScreen"