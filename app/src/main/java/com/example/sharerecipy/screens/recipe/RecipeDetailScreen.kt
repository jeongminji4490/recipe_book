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
import com.example.sharerecipy.common.theme.Beige
import com.example.sharerecipy.common.theme.BoldFont
import com.example.sharerecipy.common.theme.Navy

@Composable
fun RecipeDetailScreen(
    popUpScreen: () -> Unit
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    //val list = viewModel.info
    val list = viewModel.info

    LaunchedEffect(true) {
        viewModel.getInfo()
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
                .padding(15.dp),
            textAlign = TextAlign.Center,
            color = Navy,
            fontSize = 20.sp,
            fontFamily = BoldFont
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 메뉴얼
        ManualImageComposable(detail.manual1_img)
        ManualTextComposable(detail.manual1)

        ManualImageComposable(detail.manual2_img)
        ManualTextComposable(detail.manual2)

        ManualImageComposable(detail.manual3_img)
        ManualTextComposable(detail.manual3)

        ManualImageComposable(detail.manual4_img)
        ManualTextComposable(detail.manual4)

        ManualImageComposable(detail.manual5_img)
        ManualTextComposable(detail.manual5)

        ManualImageComposable(detail.manual6_img)
        ManualTextComposable(detail.manual6)

        ManualImageComposable(detail.manual7_img)
        ManualTextComposable(detail.manual7)

        ManualImageComposable(detail.manual8_img)
        ManualTextComposable(detail.manual8)

        ManualImageComposable(detail.manual9_img)
        ManualTextComposable(detail.manual9)

        ManualImageComposable(detail.manual10_img)
        ManualTextComposable(detail.manual10)

        ManualImageComposable(detail.manual11_img)
        ManualTextComposable(detail.manual11)

        ManualImageComposable(detail.manual12_img)
        ManualTextComposable(detail.manual12)

        ManualImageComposable(detail.manual13_img)
        ManualTextComposable(detail.manual13)

        ManualImageComposable(detail.manual14_img)
        ManualTextComposable(detail.manual14)

        ManualImageComposable(detail.manual15_img)
        ManualTextComposable(detail.manual15)

        ManualImageComposable(detail.manual16_img)
        ManualTextComposable(detail.manual16)

        ManualImageComposable(detail.manual17_img)
        ManualTextComposable(detail.manual17)

        ManualImageComposable(detail.manual18_img)
        ManualTextComposable(detail.manual18)

        ManualImageComposable(detail.manual19_img)
        ManualTextComposable(detail.manual19)

        ManualImageComposable(detail.manual20_img)
        ManualTextComposable(detail.manual20)
    }
}