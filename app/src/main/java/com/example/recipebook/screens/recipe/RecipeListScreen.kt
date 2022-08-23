package com.example.recipebook.screens.recipe

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.*
import com.example.recipebook.api.model.Recipe
import com.example.recipebook.common.composable.BasicOutlinedButton
import com.example.recipebook.common.composable.Dialog
import com.example.recipebook.common.composable.Toolbar
import com.example.recipebook.common.theme.*
import com.skydoves.landscapist.glide.GlideImage
import com.example.recipebook.R.string as AppText

@Composable
fun RecipeListScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit
) {
    val recipeList = viewModel.recipeList
    val wishList = viewModel.bookmarkList
    val category = viewModel.category

    LaunchedEffect(true) {
        viewModel.getRecipeList()
        viewModel.getBookmarkList()
        viewModel.getRecipeType()
    }

    var wishValue by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Toolbar(AppText.recipe_list, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, RECIPE_LIST_SCREEN)
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
                    items(it.list.recipes) { recipe ->
                        if (recipe.type == category.value) { // 홈화면에서 선택한 카테고리(ex 밥)와 종류가 같은 레시피만 리스트업
                            wishList.keys.forEach { wishName ->
                                if (recipe.name == wishName) { // 북마크한 레시피라면 북마크 표시
                                    wishValue = true
                                    return@forEach
                                }
                            }
                            RecipeCard(recipe, wishValue, openScreen)
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
            IconButton( // 북마크
                onClick = {
                    pickState.value = !pickState.value
                    if (pickState.value) { // 북마크 추가
                        viewModel.addBookmarkRecipe(data.name, data.ingredient, data.type)
                    } else { // 북마크 해제
                        viewModel.deleteBookmarkRecipe(data.name)
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Bookmark,
                    contentDescription = "",
                    tint = if (!pickState.value) LightGray else Navy
                )
            }

            GlideImage( // 레시피 대표이미지
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
                BasicOutlinedButton( // 조리 방법
                    AppText.how_to_cook,
                    Beige,
                    Navy,
                    Navy,
                    Modifier.padding(5.dp)
                ) { viewModel.setRecipeName(data.name, openScreen) }
            }

            if (ingredientDialog.value) { // 재료 다이얼로그
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
