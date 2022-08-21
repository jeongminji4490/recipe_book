package com.example.sharerecipy.screens.recipe

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
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
import com.example.sharerecipy.*
import com.example.sharerecipy.api.model.Recipe
import com.example.sharerecipy.common.composable.BasicOutlinedButton
import com.example.sharerecipy.common.composable.ChipComposable
import com.example.sharerecipy.common.composable.Dialog
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.*
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.string as AppText

@Composable
fun RecipeScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val recipeList = viewModel.recipeList
    val wishList = viewModel.wishList
    val type = viewModel.type

    LaunchedEffect(true) {
        viewModel.getRecipe()
        viewModel.getWishList()
        viewModel.getRecipeType()
    }

    var wishValue by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Toolbar(AppText.recipe_list, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, RECIPE_SCREEN)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Beige)
        ) {
            recipeList.value?.let {
                LazyColumn {
                    items(it.list.recipes) { item ->
                        if (item.type == type.value) {
                            wishList.keys.forEach { wishName ->
                                if (item.name == wishName) {
                                    wishValue = true
                                    return@forEach
                                }
                            }
                            RecipeCard(item, wishValue, openScreen)
                            wishValue = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(
    data: Recipe,
    value: Boolean,
    openScreen: (String) -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val ingredientDialog = remember { mutableStateOf(false) }
    val pickState = remember { mutableStateOf(value) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        backgroundColor = Beige,
        border = BorderStroke(2.dp, Navy)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    pickState.value = !pickState.value
                    if (pickState.value) {
                        viewModel.addWishRecipe(data.name, data.ingredient, data.type)
                    } else {
                        viewModel.deleteWishRecipe(data.name)
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = "",
                    tint = if (!pickState.value) LightGray else Navy
                )
            }
            GlideImage( // 레시피 메인이미지
                imageModel = data.imageUrl,
                modifier = Modifier
                    .height(150.dp)
                    .width(210.dp)
                    .padding(10.dp)
            )
            Text( // 레시피명
                text = data.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = BoldFont
            )
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                BasicOutlinedButton( // 재료
                    AppText.ingredients,
                    Navy,
                    Beige,
                    Navy,
                    Modifier.padding(5.dp)
                ) { ingredientDialog.value = true }
                Spacer(modifier = Modifier.width(10.dp))
                BasicOutlinedButton( // 방법 -> 여기서 데이터스토어에 레시피명 저장
                    AppText.how_to_cook,
                    Beige,
                    Navy,
                    Navy,
                    Modifier.padding(5.dp)
                ) { viewModel.setRecipeName(data.name, openScreen) }
            }
            if (ingredientDialog.value) {
                Dialog(
                    AppText.ingredients_dialog,
                    data.ingredient,
                    Black,
                    Icons.Filled.OutdoorGrill,
                    { ingredientDialog.value = false },
                    { ingredientDialog.value = false }
                )
            }
        }
    }
}

const val TAG = "RecipeScreen"