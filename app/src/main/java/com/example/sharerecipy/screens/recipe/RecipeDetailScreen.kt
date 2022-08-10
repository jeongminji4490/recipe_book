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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharerecipy.*
import com.example.sharerecipy.R
import com.example.sharerecipy.R.color as AppColor
import com.example.sharerecipy.common.composable.GlideImageComposable
import com.example.sharerecipy.common.composable.ManualTextComposable
import com.example.sharerecipy.common.composable.Toolbar

@Composable
fun RecipeDetailScreen(
    viewModel: ViewModel,
    openAndPopUp: (String, String) -> Unit
) {
    viewModel.getInfoTest()
    val list = viewModel.infos.observeAsState().value
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            Toolbar(R.string.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(RECIPE_SCREEN, RECIPE_DETAIL_SCREEN)
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
                list?.let { data ->
                    RecipeDetailContent(data)
                }
            }
        }
    )
}

@Composable
fun RecipeDetailContent(detail: RecipeInfo) {
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
        if (detail.manual1_img != "") GlideImageComposable(detail.manual1_img)
        if (detail.manual1 != "") ManualTextComposable(detail.manual1)

        if (detail.manual2_img != "") GlideImageComposable(detail.manual2_img)
        if (detail.manual2 != "") ManualTextComposable(detail.manual2)

        if (detail.manual3_img != "") GlideImageComposable(detail.manual3_img)
        if (detail.manual3 != "") ManualTextComposable(detail.manual3)

        if (detail.manual4_img != "") GlideImageComposable(detail.manual4_img)
        if (detail.manual4 != "") ManualTextComposable(detail.manual4)

        if (detail.manual5_img != "") GlideImageComposable(detail.manual5_img)
        if (detail.manual5 != "") ManualTextComposable(detail.manual5)

        if (detail.manual6_img != "") GlideImageComposable(detail.manual6_img)
        if (detail.manual6 != "") ManualTextComposable(detail.manual6)

        if (detail.manual7_img != "") GlideImageComposable(detail.manual7_img)
        if (detail.manual7 != "") ManualTextComposable(detail.manual7)

        if (detail.manual8_img != "") GlideImageComposable(detail.manual8_img)
        if (detail.manual8 != "") ManualTextComposable(detail.manual8)

        if (detail.manual9_img != "") GlideImageComposable(detail.manual9_img)
        if (detail.manual9 != "") ManualTextComposable(detail.manual9)

        if (detail.manual10_img != "") GlideImageComposable(detail.manual10_img)
        if (detail.manual10 != "") ManualTextComposable(detail.manual10)

        if (detail.manual11_img != "") GlideImageComposable(detail.manual11_img)
        if (detail.manual11 != "") ManualTextComposable(detail.manual11)

        if (detail.manual12_img != "") GlideImageComposable(detail.manual12_img)
        if (detail.manual12 != "") ManualTextComposable(detail.manual12)

        if (detail.manual13_img != "") GlideImageComposable(detail.manual13_img)
        if (detail.manual13 != "") ManualTextComposable(detail.manual13)

        if (detail.manual14_img != "") GlideImageComposable(detail.manual14_img)
        if (detail.manual14 != "") ManualTextComposable(detail.manual14)

        if (detail.manual15_img != "") GlideImageComposable(detail.manual15_img)
        if (detail.manual15 != "") ManualTextComposable(detail.manual15)

        if (detail.manual16_img != "") GlideImageComposable(detail.manual16_img)
        if (detail.manual16 != "") ManualTextComposable(detail.manual16)

        if (detail.manual17_img != "") GlideImageComposable(detail.manual17_img)
        if (detail.manual17 != "") ManualTextComposable(detail.manual17)

        if (detail.manual18_img != "") GlideImageComposable(detail.manual18_img)
        if (detail.manual18 != "") ManualTextComposable(detail.manual18)

        if (detail.manual19_img != "") GlideImageComposable(detail.manual19_img)
        if (detail.manual19 != "") ManualTextComposable(detail.manual19)

        if (detail.manual20_img != "") GlideImageComposable(detail.manual20_img)
        if (detail.manual20 != "") ManualTextComposable(detail.manual20)
    }
}