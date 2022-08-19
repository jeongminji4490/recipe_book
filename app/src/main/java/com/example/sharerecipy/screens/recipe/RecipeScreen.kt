package com.example.sharerecipy.screens.recipe

import android.util.Log
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
    openAndPopUp: (String, String) -> Unit) {

    val viewModel : RecipeViewModel = hiltViewModel()
    val recipeList = viewModel.recipeList
    val wishList = viewModel.wishList

    LaunchedEffect(true){
        viewModel.getRecipe()
        viewModel.getWishList()
    }

    var wishValue by rememberSaveable { mutableStateOf(false) }
    var type by rememberSaveable { mutableStateOf("ALL") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Toolbar(AppText.recipe_list, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, RECIPE_SCREEN) }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(White)
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(15.dp),

                verticalAlignment = Alignment.CenterVertically) {

                ChipComposable(AppText.all, Icons.Filled.RiceBowl) { type = "ALL" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.rice, Icons.Filled.RiceBowl) { type = "밥" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.dessert, Icons.Filled.Cookie) { type = "후식" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.soup, Icons.Filled.SoupKitchen) { type = "국&찌개" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.side, Icons.Filled.EggAlt) { type = "반찬" }
                Spacer(Modifier.width(5.dp))

                ChipComposable(AppText.ilpoom, Icons.Filled.RamenDining) { type = "일품" }
            }
            recipeList.value?.let {
                when (type) {
                    "ALL" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                            wishList.forEach { wishName ->
                                if (item.name == wishName.key){
                                    wishValue = true
                                    return@forEach
                                }
                            }
                            RecipeCard(item, wishValue, openScreen)
                            wishValue = false
                        }
                    }
                    "밥" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                                if (item.type == "밥"){
                                    wishList.forEach { wishName ->
                                        if (item.name == wishName.key){
                                            wishValue = true
                                            return@forEach
                                        }
                                    }
                                    RecipeCard(item, wishValue, openScreen)
                                    wishValue = false
                                }
                            }
                        }
                    "후식" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                                if (item.type == "후식"){
                                    wishList.forEach { wishName ->
                                        if (item.name == wishName.key){
                                            wishValue = true
                                            return@forEach
                                        }
                                    }
                                    RecipeCard(item, wishValue, openScreen)
                                    wishValue = false
                                }
                            }
                        }
                    "국&찌개" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                                if (item.type == "국&찌개"){
                                    wishList.forEach { wishName ->
                                        if (item.name == wishName.key){
                                            wishValue = true
                                            return@forEach
                                        }
                                    }
                                    RecipeCard(item, wishValue, openScreen)
                                    wishValue = false
                                }
                            }
                        }
                    "반찬" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                                if (item.type == "반찬"){
                                    wishList.forEach { wishName ->
                                        if (item.name == wishName.key){
                                            wishValue = true
                                            return@forEach
                                        }
                                    }
                                    RecipeCard(item, wishValue, openScreen)
                                    wishValue = false
                                }
                            }
                        }
                    "일품" ->
                        LazyColumn {
                            items(it.list.recipes) { item ->
                                if (item.type == "일품"){
                                    wishList.forEach { wishName ->
                                        if (item.name == wishName.key){
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
}

@Composable
fun RecipeCard(
    data: Recipe,
    value: Boolean,
    openScreen: (String) -> Unit
){
    val viewModel: RecipeViewModel = hiltViewModel()
    val ingredientDialog = remember { mutableStateOf(false) }
    val pickState = remember { mutableStateOf(value) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    pickState.value = !pickState.value
                    if (pickState.value){
                        viewModel.addWishRecipe(data.name, data.ingredient)
                    }else {
                        viewModel.deleteWishRecipe(data.name, data.ingredient)
                    } },
            ) {
                Icon(
                    Icons.Filled.Bookmark,
                    contentDescription = "",
                    tint = if (!pickState.value) LightGray else LightOrange
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
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                BasicOutlinedButton( // 재료
                    AppText.ingredients,
                    White,
                    Black,
                    Black,
                    Modifier.padding(5.dp) )
                { ingredientDialog.value = true }
                Spacer(modifier = Modifier.width(10.dp))
                BasicOutlinedButton( // 방법 -> 여기서 데이터스토어에 레시피명 저장
                    AppText.how_to_cook,
                    LightOrange,
                    White,
                    LightOrange,
                    Modifier.padding(5.dp))
                { viewModel.setRecipeName(data.name, openScreen) }
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