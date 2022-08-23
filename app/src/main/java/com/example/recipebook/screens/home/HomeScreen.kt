package com.example.recipebook.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebook.*
import com.example.recipebook.R.*
import com.example.recipebook.common.composable.*
import com.example.recipebook.common.theme.*
import com.example.recipebook.screens.drawer.Drawer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import com.example.recipebook.R.string as AppText

@Composable
fun HomeScreen(
    openAndPopUp: (String, String) -> Unit
) {
    // Drawer
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        openAndPopUp(route, HOME_SCREEN)
                    },
                    openAndPopUp = openAndPopUp
                )
            }
        ) {
            Scaffold(
                topBar = {
                    Toolbar(AppText.home, Icons.Filled.Menu) {
                        openDrawer()
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Beige)
                        .padding(it)
                ) {
                    HomeContent(openAndPopUp)
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val currentUser = Firebase.auth.currentUser // 로그인한 사용자
    var name by rememberSaveable { mutableStateOf("") } // 닉네임
    val scrollState = rememberScrollState()

    name = currentUser?.displayName.toString()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp)
        ) {
            Text(
                text = stringResource(AppText.hi),
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = LightFont
            )
            Spacer(Modifier.width(5.dp))
            Text( // 닉네임
                text = name,
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = BoldFont
            )
            Text(
                text = stringResource(AppText._hi),
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = LightFont
            )
        }
        Text(
            text = stringResource(AppText.welcome),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 20.dp),
            color = Navy,
            fontSize = 18.sp,
            fontFamily = LightFont
        )

        CategoryTextComposable(AppText.category)

        // 레시피 카테고리 (밥, 국 & 찌개, 반찬)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            MenuCardComposable(AppText.rice, Icons.Filled.RiceBowl)
            { viewModel.setRecipeType("밥", openAndPopUp) }

            MenuCardComposable(AppText.soup, Icons.Filled.SoupKitchen)
            { viewModel.setRecipeType("국&찌개", openAndPopUp) }

            MenuCardComposable(AppText.side, Icons.Filled.EggAlt)
            { viewModel.setRecipeType("반찬", openAndPopUp) }
        }

        // 레시피 카테고리 (후식, 일품, 찜한 레시피)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            MenuCardComposable(AppText.dessert, Icons.Filled.Cookie)
            { viewModel.setRecipeType("후식", openAndPopUp) }

            MenuCardComposable(AppText.ilpoom, Icons.Filled.RamenDining)
            { viewModel.setRecipeType("일품", openAndPopUp) }

            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable { openAndPopUp(BOOKMARK_SCREEN, HOME_SCREEN) },
                backgroundColor = Beige,
                border = BorderStroke(2.dp, Navy),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(AppText.wish_list),
                        color = Navy,
                        fontSize = 20.sp,
                        fontFamily = BoldFont
                    )
                    Icon(
                        imageVector = Icons.Filled.Bookmark,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 8.dp),
                        tint = Navy
                    )
                }
            }
        }

        CategoryTextComposable(AppText.news)

        // 레시피 관련 기사
        ArticleCardComposable(
            AppText.article1,
            "https://newsis.com/view/?id=NISX20220816_0001979360&cID=13001&pID=13000"
        )
        ArticleCardComposable(
            AppText.article2,
            "https://www.wikitree.co.kr/articles/523470")
    }
}