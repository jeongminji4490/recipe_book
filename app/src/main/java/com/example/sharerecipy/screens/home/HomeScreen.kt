package com.example.sharerecipy.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R.*
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.SETTING_SCREEN
import com.example.sharerecipy.WISH_LIST_SCREEN
import com.example.sharerecipy.common.composable.IconOutlinedButton
import com.example.sharerecipy.common.composable.Toolbar
import com.example.sharerecipy.common.theme.Black
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.common.theme.White
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.sharerecipy.R.string as AppText

@Composable
fun HomeScreen(openAndPopUp: (String, String) -> Unit){
    val currentUser = Firebase.auth.currentUser // 로그인한 사용자
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") } // 닉네임

    email = currentUser?.email.toString() // 사용자 이메일 가져오기
    name = currentUser?.displayName.toString()

    // Scafford 위로


    HomeContent(email, name, openAndPopUp)
}

@Composable
fun HomeContent(
    email: String,
    name: String,
    openAndPopUp: (String, String) -> Unit
) {
    Scaffold(
        topBar = {
            Toolbar(title = AppText.app_name_version_2, Icons.Filled.Home) {  }
        },
        backgroundColor = White,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Card( // 회원 정보
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Text( // 이메일
                            modifier = Modifier.fillMaxWidth(),
                            text = email,
                            textAlign = TextAlign.Left,
                            color = Black,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text( // 닉네임
                            modifier = Modifier.fillMaxWidth(),
                            text = name,
                            textAlign = TextAlign.Left,
                            color = Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            IconOutlinedButton(
                                AppText.setting,
                                Black,
                                White,
                                Black,
                                Black,
                                Icons.Filled.Settings,
                                "setting",
                                Modifier.height(40.dp)
                            ) { openAndPopUp(SETTING_SCREEN, HOME_SCREEN) }
                            IconOutlinedButton(
                                AppText.wish_list,
                                LightOrange,
                                LightOrange,
                                White,
                                White,
                                Icons.Filled.Favorite,
                                "favorite",
                                Modifier
                                    .height(40.dp)
                            ) { openAndPopUp(WISH_LIST_SCREEN, HOME_SCREEN) }
                        }
                    }
                }
                IconOutlinedButton(
                    AppText.show_recipe,
                    LightOrange,
                    LightOrange,
                    White,
                    White,
                    Icons.Filled.OutdoorGrill,
                    "recipe_list",
                    Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) { openAndPopUp(RECIPE_SCREEN, HOME_SCREEN) }
                IconOutlinedButton(
                    AppText.my_recipe,
                    LightOrange,
                    LightOrange,
                    White,
                    White,
                    Icons.Filled.Grade,
                    "my_recipe",
                    Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .padding(15.dp))
                { }
            } },
    )
}

//        Card( // 레시피 보기
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(15.dp)
//                .clickable { },
//            backgroundColor = colorResource(AppColor.lightOrange),
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
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//        Card( // 내 레시피
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(15.dp),
//            backgroundColor = colorResource(AppColor.lightOrange),
//            //elevation = 10.dp,
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(15.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                //Spacer(modifier = Modifier.width(10.dp))
//                Text(
//                    text = stringResource(AppText.my_recipe),
//                    modifier = Modifier.fillMaxWidth(),
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }


const val TAG = "HomeScreen"