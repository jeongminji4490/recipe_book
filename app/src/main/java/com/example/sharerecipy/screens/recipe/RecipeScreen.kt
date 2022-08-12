package com.example.sharerecipy.screens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.sharerecipy.common.composable.Dialog
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.*
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.string as AppText

@Composable
fun RecipeScreen(
    viewModel: ViewModel,
    openAndPopUp: (String, String) -> Unit) {

    val list = viewModel.list.observeAsState().value
    Scaffold(
        topBar = {
            Toolbar(AppText.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, RECIPE_SCREEN)
        } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(LightOrange)
        ) {
            list?.let {
                LazyColumn {
                    items(it.list.recipes) { item ->
                        RecipeCard(item, openAndPopUp)
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(
    data: Recipe,
    openAndPopUp: (String, String) -> Unit,
){
    val viewModel: ViewModel = hiltViewModel()
    val ingredientDialog = remember { mutableStateOf(false) }
    val pickState = remember { mutableStateOf(false) }
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
                onClick = { pickState.value = !pickState.value },
            ) {
                Icon(
                    Icons.Filled.Favorite,
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
                { viewModel.setRecipeName(data.name, openAndPopUp) }
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