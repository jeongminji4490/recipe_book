package com.example.sharerecipy.screens.recipe

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.sharerecipy.common.composable.BasicOutlinedButton
import com.example.sharerecipy.common.composable.DialogConfirmButton
import com.example.sharerecipy.common.composable.Toolbar
import com.skydoves.landscapist.glide.GlideImage
import com.example.sharerecipy.R.color as AppColor
import com.example.sharerecipy.R.string as AppText

@Composable
fun RecipeScreen(
    viewModel: ViewModel,
    openAndPopUp: (String, String) -> Unit
) {
    val list = viewModel.list.observeAsState().value
    Scaffold(
        topBar = {
            Toolbar(AppText.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(HOME_SCREEN, RECIPE_SCREEN)
        } },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(colorResource(AppColor.lightOrange))
            ) {
                list?.let {
                    LazyColumn {
                        items(it.list.recipes) { item ->
                            RecipeCard(item, viewModel, openAndPopUp)
                        }
                    }
                }
            }
        }
    )
//    list?.let {
//        LazyColumn {
//            items(it.list.recipes) { item ->
//                RecipeCard(item, openAndPopUp)
//            }
//        }
//    }
}

@Composable
fun RecipeCard(
    data: Recipe,
    viewModel: ViewModel,
    openAndPopUp: (String, String) -> Unit){
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
                    tint = if (!pickState.value) Color.LightGray else Color.Red
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
                    AppColor.white,
                    AppColor.black,
                    AppColor.black,
                    Modifier.padding(5.dp) )
                { ingredientDialog.value = true }
                Spacer(modifier = Modifier.width(10.dp))
                BasicOutlinedButton( // 방법 -> 여기서 데이터스토어에 값 저장 ? // 레시피명 공백 -> _로 대체
                    AppText.how_to_cook,
                    AppColor.lightOrange,
                    AppColor.white,
                    AppColor.lightOrange,
                    Modifier.padding(5.dp))
                {
                    //val name = data.name.replace(" ","_")
                    //Log.e("RECIPE_SCREEN", name)
                    viewModel.setRecipeName(data.name, openAndPopUp)
                }
            }
            if (ingredientDialog.value) {
                AlertDialog( // 재료를 보여줄 다이얼로그
                    backgroundColor = Color.White,
                    onDismissRequest = {
                        ingredientDialog.value = false
                    },
                    title = {
                        Row {
                            Icon(
                                Icons.Filled.OutdoorGrill,
                                contentDescription = "",
                                tint = colorResource(AppColor.black)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "재료 목록",
                                color = Color.Black)
                        }
                    },
                    text = {
                        Text(
                            text = data.ingredient,
                            color = Color.Black)
                    },
                    confirmButton = {
                        DialogConfirmButton(
                            AppText.OK,
                            action = { ingredientDialog.value = false },
                            AppColor.lightOrange,
                            AppColor.white
                        )
                    }
                )
            }
        }
    }
}