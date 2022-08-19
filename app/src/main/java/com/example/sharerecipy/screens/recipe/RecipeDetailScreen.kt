package com.example.sharerecipy.screens.recipe

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.*
import com.example.sharerecipy.R
import com.example.sharerecipy.api.model.Recipe
import com.example.sharerecipy.R.color as AppColor
import com.example.sharerecipy.R.string as AppText
import com.example.sharerecipy.common.composable.ManualImageComposable
import com.example.sharerecipy.common.composable.ManualTextComposable
import com.example.sharerecipy.common.composable.Toolbar

@Composable
fun RecipeDetailScreen(
    popUpScreen: () -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val list = viewModel.info

    LaunchedEffect(true) {
        viewModel.getInfo()
    }

    Scaffold(
        topBar = {
            Toolbar(AppText.method, Icons.Filled.ArrowBack) {
                //openAndPopUp(RECIPE_SCREEN, RECIPE_DETAIL_SCREEN)
                popUpScreen()
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(scrollState)
                    .background(colorResource(R.color.white))
            ) {
                list.value?.let { data ->
                    RecipeDetailContent(data)
                }
            }
        }
    )
}

@Composable
fun RecipeDetailContent(detail: Recipe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( // 레시피명
            text = detail.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(colorResource(AppColor.lightOrange)),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 메뉴얼
        if (detail.manual1_img != "") ManualImageComposable(detail.manual1_img)
        if (detail.manual1 != "") ManualTextComposable(detail.manual1)

        if (detail.manual2_img != "") ManualImageComposable(detail.manual2_img)
        if (detail.manual2 != "") ManualTextComposable(detail.manual2)

        if (detail.manual3_img != "") ManualImageComposable(detail.manual3_img)
        if (detail.manual3 != "") ManualTextComposable(detail.manual3)

        if (detail.manual4_img != "") ManualImageComposable(detail.manual4_img)
        if (detail.manual4 != "") ManualTextComposable(detail.manual4)

        if (detail.manual5_img != "") ManualImageComposable(detail.manual5_img)
        if (detail.manual5 != "") ManualTextComposable(detail.manual5)

        if (detail.manual6_img != "") ManualImageComposable(detail.manual6_img)
        if (detail.manual6 != "") ManualTextComposable(detail.manual6)

        if (detail.manual7_img != "") ManualImageComposable(detail.manual7_img)
        if (detail.manual7 != "") ManualTextComposable(detail.manual7)

        if (detail.manual8_img != "") ManualImageComposable(detail.manual8_img)
        if (detail.manual8 != "") ManualTextComposable(detail.manual8)

        if (detail.manual9_img != "") ManualImageComposable(detail.manual9_img)
        if (detail.manual9 != "") ManualTextComposable(detail.manual9)

        if (detail.manual10_img != "") ManualImageComposable(detail.manual10_img)
        if (detail.manual10 != "") ManualTextComposable(detail.manual10)

        if (detail.manual11_img != "") ManualImageComposable(detail.manual11_img)
        if (detail.manual11 != "") ManualTextComposable(detail.manual11)

        if (detail.manual12_img != "") ManualImageComposable(detail.manual12_img)
        if (detail.manual12 != "") ManualTextComposable(detail.manual12)

        if (detail.manual13_img != "") ManualImageComposable(detail.manual13_img)
        if (detail.manual13 != "") ManualTextComposable(detail.manual13)

        if (detail.manual14_img != "") ManualImageComposable(detail.manual14_img)
        if (detail.manual14 != "") ManualTextComposable(detail.manual14)

        if (detail.manual15_img != "") ManualImageComposable(detail.manual15_img)
        if (detail.manual15 != "") ManualTextComposable(detail.manual15)

        if (detail.manual16_img != "") ManualImageComposable(detail.manual16_img)
        if (detail.manual16 != "") ManualTextComposable(detail.manual16)

        if (detail.manual17_img != "") ManualImageComposable(detail.manual17_img)
        if (detail.manual17 != "") ManualTextComposable(detail.manual17)

        if (detail.manual18_img != "") ManualImageComposable(detail.manual18_img)
        if (detail.manual18 != "") ManualTextComposable(detail.manual18)

        if (detail.manual19_img != "") ManualImageComposable(detail.manual19_img)
        if (detail.manual19 != "") ManualTextComposable(detail.manual19)

        if (detail.manual20_img != "") ManualImageComposable(detail.manual20_img)
        if (detail.manual20 != "") ManualTextComposable(detail.manual20)
    }
}