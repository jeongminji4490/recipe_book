package com.example.sharerecipy.screens.home

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharerecipy.*
import com.example.sharerecipy.R.*
import com.example.sharerecipy.common.composable.CardComposable
import com.example.sharerecipy.common.composable.IconOutlinedButton
import com.example.sharerecipy.common.composable.TestCardComposable
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.*
import com.example.sharerecipy.screens.drawer.Drawer
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import com.example.sharerecipy.R.string as AppText

@Composable
fun HomeScreen(
    openAndPopUp: (String, String) -> Unit
) {
    val currentUser = Firebase.auth.currentUser // 로그인한 사용자
    var name by rememberSaveable { mutableStateOf("") } // 닉네임
    val scrollState = rememberScrollState()

    name = currentUser?.displayName.toString()

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
                    HomeContent(name, scrollState, openAndPopUp)
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    name: String,
    scrollState: ScrollState,
    openAndPopUp: (String, String) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    Column{
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
            Text( // 닉네임
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
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp),
            horizontalArrangement = Arrangement.Start) {
            Icon(
                Icons.Filled.Circle,
                "",
                Modifier
                    .size(15.dp)
                    .padding(top = 8.dp),
                tint = Navy)
            Text(
                text = stringResource(AppText.category),
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = BoldFont
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceBetween) {

            TestCardComposable(AppText.rice, Icons.Filled.RiceBowl)
            { viewModel.setRecipeType("밥", openAndPopUp) }

            TestCardComposable(AppText.soup, Icons.Filled.SoupKitchen)
            { viewModel.setRecipeType("국&찌개", openAndPopUp) }

            TestCardComposable(AppText.side, Icons.Filled.EggAlt)
            { viewModel.setRecipeType("반찬", openAndPopUp) }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TestCardComposable(AppText.dessert, Icons.Filled.Cookie)
            { viewModel.setRecipeType("후식", openAndPopUp) }

            TestCardComposable(AppText.ilpoom, Icons.Filled.RamenDining)
            { viewModel.setRecipeType("일품", openAndPopUp) }

            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable { openAndPopUp(WISH_LIST_SCREEN, HOME_SCREEN) },
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
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 8.dp),
                        tint = Navy)
                }
            }
        }
        Column(
            Modifier.padding(20.dp),
           // horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}

const val TAG = "HomeScreen"