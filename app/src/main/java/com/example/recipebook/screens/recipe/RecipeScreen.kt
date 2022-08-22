package com.example.recipebook.screens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.api.model.Recipe
import com.example.recipebook.R.string as AppText
import com.example.recipebook.common.composable.ManualImageComposable
import com.example.recipebook.common.composable.ManualTextComposable
import com.example.recipebook.common.composable.Toolbar
import com.example.recipebook.common.theme.Beige
import com.example.recipebook.common.theme.BoldFont
import com.example.recipebook.common.theme.Navy

@Composable
fun RecipeScreen(
    popUpScreen: () -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val recipe = viewModel.recipe

    LaunchedEffect(true) {
        viewModel.getRecipe()
    }

    Scaffold(
        topBar = {
            Toolbar(AppText.method, Icons.Filled.ArrowBack) {
                popUpScreen()
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(scrollState)
                    .background(Beige)
            ) {
                recipe.value?.let { recipe ->
                    RecipeContent(recipe)
                }
            }
        }
    )
}

@Composable
fun RecipeContent(recipe: Recipe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( // 레시피명
            text = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            textAlign = TextAlign.Center,
            color = Navy,
            fontSize = 20.sp,
            fontFamily = BoldFont
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 조리 순서
        ManualImageComposable(recipe.manual1_img)
        ManualTextComposable(recipe.manual1)

        ManualImageComposable(recipe.manual2_img)
        ManualTextComposable(recipe.manual2)

        ManualImageComposable(recipe.manual3_img)
        ManualTextComposable(recipe.manual3)

        ManualImageComposable(recipe.manual4_img)
        ManualTextComposable(recipe.manual4)

        ManualImageComposable(recipe.manual5_img)
        ManualTextComposable(recipe.manual5)

        ManualImageComposable(recipe.manual6_img)
        ManualTextComposable(recipe.manual6)

        ManualImageComposable(recipe.manual7_img)
        ManualTextComposable(recipe.manual7)

        ManualImageComposable(recipe.manual8_img)
        ManualTextComposable(recipe.manual8)

        ManualImageComposable(recipe.manual9_img)
        ManualTextComposable(recipe.manual9)

        ManualImageComposable(recipe.manual10_img)
        ManualTextComposable(recipe.manual10)

        ManualImageComposable(recipe.manual11_img)
        ManualTextComposable(recipe.manual11)

        ManualImageComposable(recipe.manual12_img)
        ManualTextComposable(recipe.manual12)

        ManualImageComposable(recipe.manual13_img)
        ManualTextComposable(recipe.manual13)

        ManualImageComposable(recipe.manual14_img)
        ManualTextComposable(recipe.manual14)

        ManualImageComposable(recipe.manual15_img)
        ManualTextComposable(recipe.manual15)

        ManualImageComposable(recipe.manual16_img)
        ManualTextComposable(recipe.manual16)

        ManualImageComposable(recipe.manual17_img)
        ManualTextComposable(recipe.manual17)

        ManualImageComposable(recipe.manual18_img)
        ManualTextComposable(recipe.manual18)

        ManualImageComposable(recipe.manual19_img)
        ManualTextComposable(recipe.manual19)

        ManualImageComposable(recipe.manual20_img)
        ManualTextComposable(recipe.manual20)
    }
}