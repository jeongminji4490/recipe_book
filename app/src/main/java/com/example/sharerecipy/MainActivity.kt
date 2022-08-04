package com.example.sharerecipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null // 회원가입 및 로그인을 위한 FirebaseAuth instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            val navController = rememberNavController()
//            //val context = LocalContext.current
//            MaterialTheme {
//                NavigationComponent(navController)
//            }
//        }
    }

//    @Composable
//    fun NavigationComponent(navController: NavHostController) {
//        NavHost(
//            navController = navController,
//            startDestination = "login"
//        ){
//            composable("login") {
//                LoginPreview(navController)
//            }
//        }
//    }
//
//    @Composable
//    fun LoginPreview(navController: NavController) {
//        Scaffold( // Material Layout
//            content = {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(it)
//                ) { }
//                LoginScreen(navController) },
//            backgroundColor = Color.Black
//        )
//    }
//
//    // 상태 호이스팅 패턴으로 구현할 시 MainContent에 전달해야할 인자가 많아짐(이건 데이터클래스로 해결할 수 있을 것 같긴함)
//    // 하지민 MainContent는 여기서만 쓰이는 컴포저블인데 상태 호이스팅 패턴으로 구성할 필요가 있을까?
//
//    @Composable
//    fun LoginScreen(navController: NavController){
//        // mutableStateOf : 관찰 가능한 MutableState
//        auth = Firebase.auth
//        val context = LocalContext.current
//        var id by remember { mutableStateOf("") }
//        var pw by remember { mutableStateOf("") }
//        var progressState by remember { mutableStateOf(0) }
//
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(40.dp))
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "MY",
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.Bold)
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "RECIPE",
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.Bold)
//            GlideImage(
//                imageModel = R.drawable.vegetables,
//                modifier = Modifier
//                    .height(150.dp)
//                    .width(210.dp)
//            )
//            OutlinedTextField( // id 입력을 위한 TextField
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = id,
//                onValueChange = { id = it },
//                label = { Text("Email", color = Color.White ) },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.White,
//                    focusedBorderColor = colorResource(R.color.mint),
//                    unfocusedBorderColor = Color.White
//                )
//            )
//            OutlinedTextField( // 비밀번호 입력을 위한 TextField
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = pw,
//                onValueChange = { pw = it },
//                label = { Text(text = "Password", color = Color.White ) },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.White,
//                    focusedBorderColor = colorResource(R.color.mint),
//                    unfocusedBorderColor = Color.White
//                ),
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(
//                onClick = { // 로그인
//                    if (id.isNotEmpty() && pw.isNotEmpty()){
//                        progressState = 1
//                        auth?.signInWithEmailAndPassword(id, pw)
//                            ?.addOnCompleteListener { task ->
//                                if (task.isSuccessful){ // 성공 시 메인화면으로 이동
//                                    Toast.makeText(
//                                        context,
//                                        "로그인 완료",
//                                        Toast.LENGTH_SHORT).show()
//                                }else { // 실패 시 진행바 중지 & 에러 토스트
//                                    progressState = 0
//                                    try{
//                                        throw task.exception!!
//                                    }catch (e : FirebaseAuthInvalidUserException){
//                                        Toast.makeText(context, "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show()
//                                    }catch (e: FirebaseAuthInvalidCredentialsException){
//                                        Toast.makeText(context, "이메일 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
//                                    }catch (e: FirebaseAuthException){
//                                        Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
//                                    }
//                                }
//                            }
//                    }else {
//                        Toast.makeText(
//                            context,
//                            "아이디 또는 비밀번호를 입력해주세요",
//                            Toast.LENGTH_SHORT).show()
//                    } },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Black,
//                    contentColor = colorResource(R.color.mint)
//                )
//            ){
//                Text("LOGIN")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            Button(
//                onClick = { // 회원가입 페이지로 이동(지금은 테스트중이라 메인화면으로 이동하도록 설정)
//                    //onNavigate(R.id.action_loginFragment_to_joinFragment)
//                          },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                elevation = null,
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Black,
//                    contentColor = Color.White)
//            ) {
//                Text(text = "JOIN")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            if (progressState == 1){ // 1이 되면 진행바 실행
//                CircularProgressIndicator(
//                    modifier = Modifier.padding(16.dp),
//                    color = colorResource(R.color.mint),
//                    strokeWidth = Dp(value = 4F)
//                )
//            }
//        }
//    }
//
//    @Composable
//    fun LoginTest(popUpScreen: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
//        val uiState by viewModel.uiState
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            Spacer(modifier = Modifier.height(40.dp))
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "MY",
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.Bold)
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "RECIPE",
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                fontSize = 40.sp,
//                fontWeight = FontWeight.Bold)
//            GlideImage(
//                imageModel = R.drawable.vegetables,
//                modifier = Modifier
//                    .height(150.dp)
//                    .width(210.dp)
//            )
//            OutlinedTextField( // id 입력을 위한 TextField
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = uiState.email,
//                onValueChange = { uiState.email = it },
//                label = { Text("Email", color = Color.White ) },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.White,
//                    focusedBorderColor = colorResource(R.color.mint),
//                    unfocusedBorderColor = Color.White
//                )
//            )
//            OutlinedTextField( // 비밀번호 입력을 위한 TextField
//                modifier = Modifier
//                    .fillMaxWidth(),
//                value = uiState.password,
//                onValueChange = { uiState.password = it },
//                label = { Text(text = "Password", color = Color.White ) },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.White,
//                    focusedBorderColor = colorResource(R.color.mint),
//                    unfocusedBorderColor = Color.White
//                ),
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//        }
//    }
//
//    override fun onStart() { // 자동 로그인
//        super.onStart()
//        auth = Firebase.auth
//        val currentUser = auth?.currentUser
//        if (currentUser != null){
//
//        }
//    }
}