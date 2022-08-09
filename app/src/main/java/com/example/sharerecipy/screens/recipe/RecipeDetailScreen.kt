package com.example.sharerecipy.screens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.example.sharerecipy.*
import com.example.sharerecipy.common.composable.Toolbar
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailScreen(
    viewModel: ViewModel,
    openAndPopUp: (String, String) -> Unit
){
    viewModel.getInfoTest()
    val list = viewModel.infos.observeAsState().value
    Scaffold(
        topBar = {
            Toolbar(R.string.app_name_version_2, Icons.Filled.ArrowBack) {
                openAndPopUp(RECIPE_SCREEN, RECIPE_DETAIL_SCREEN)
            } },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(colorResource(R.color.white))
            ) {
                list?.let { data ->
                    RecipeDetailCard(data)
//                    LazyColumn {
//                        items(it.list.recipeInfo) { item ->
//                            RecipeDetailCard(item)
//                        }
//                    }
                }
            }
        }
    )
}

@Composable
fun RecipeDetailCard(detail: RecipeInfo){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text( // 레시피명
                text = detail.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            // 메뉴얼
            Text(
                text = detail.manual1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = detail.manual2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = detail.manual2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}