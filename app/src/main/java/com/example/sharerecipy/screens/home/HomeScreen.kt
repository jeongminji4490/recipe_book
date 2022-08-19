package com.example.sharerecipy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R.*
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.SETTING_SCREEN
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.CardComposable
import com.example.sharerecipy.common.composable.IconOutlinedButton
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
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") } // 닉네임

    email = currentUser?.email.toString() // 사용자 이메일 가져오기
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
                    HomeContent(email, name, openAndPopUp)
                }
            }
        }
    }

//    Scaffold(
//        topBar = {
//            Toolbar(AppText.home, Icons.Filled.Menu) {
//                openDrawer()
//            }
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Beige)
//                .padding(it)
//        ) {
//            HomeContent(email, name, openAndPopUp)
//        }
//    }
}

@Composable
fun HomeContent(
    email: String,
    name: String,
    openAndPopUp: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text( // 이메일
                text = "Hi, ",
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(5.dp))
            Text( // 닉네임
                text = name,
                textAlign = TextAlign.Left,
                color = Navy,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Medium
            )
            //Spacer(modifier = Modifier.height(10.dp))
//            CardComposable(AppText.show_recipe) { openAndPopUp(RECIPE_SCREEN, HOME_SCREEN) }
//            CardComposable(AppText.my_recipe) { }
        }
        Column(
            Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardComposable(AppText.show_recipe) { openAndPopUp(RECIPE_SCREEN, HOME_SCREEN) }
            CardComposable(AppText.my_recipe) { }
        }
    }
//        Card( // 레시피 보기
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(15.dp)
//                .clickable { openAndPopUp(RECIPE_SCREEN, HOME_SCREEN) },
//            backgroundColor = LightOrange,
//            //elevation = 0.dp,
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(15.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                //Spacer(modifier = Modifier.width(10.dp))
//                Text(
//                    text = stringResource(AppText.show_recipe),
//                    modifier = Modifier.fillMaxWidth(),
//                    color = White,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//        IconOutlinedButton(
//            AppText.show_recipe,
//            LightOrange,
//            LightOrange,
//            White,
//            White,
//            Icons.Filled.OutdoorGrill,
//            "recipe_list",
//            Modifier
//                .height(80.dp)
//                .fillMaxWidth()
//                .padding(15.dp)
//        ) { openAndPopUp(RECIPE_SCREEN, HOME_SCREEN) }
//        IconOutlinedButton(
//            AppText.my_recipe,
//            LightOrange,
//            LightOrange,
//            White,
//            White,
//            Icons.Filled.Grade,
//            "my_recipe",
//            Modifier
//                .height(80.dp)
//                .fillMaxWidth()
//                .padding(15.dp)
//        )
//        { }

}

const val TAG = "HomeScreen"