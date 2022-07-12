package com.example.sharerecipy

import android.app.Activity.RESULT_OK
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.landscapist.glide.GlideImage

class LoginFragment : Fragment() {

    private var auth : FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    auth = Firebase.auth
                    loginView()
                }
            }
        }
    }

    @Composable
    fun loginView() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                mainContent(onNavigate = { dest -> findNavController().navigate(dest) }) },
            backgroundColor = Color.Black
        )
    }

    @Composable
    fun mainContent(onNavigate: (Int) -> Unit){
        var id by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        var progressState by remember { mutableStateOf(0) }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "MY",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "RECIPE",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            GlideImage(
                imageModel = R.drawable.vegetables,
                modifier = Modifier
                    .height(150.dp)
                    .width(210.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = id,
                onValueChange = { id = it },
                label = { Text("ID", color = Color.White ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = colorResource(R.color.mint),
                    unfocusedBorderColor = Color.White
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = pw,
                onValueChange = { pw = it },
                label = { Text(text = "Password", color = Color.White ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = colorResource(R.color.mint),
                    unfocusedBorderColor = Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button( // 로그인
                onClick = {
                    if (id.isNotEmpty() && pw.isNotEmpty()){
                        progressState = 1
                        auth?.signInWithEmailAndPassword(id, pw)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(
                                        requireContext(),
                                        "로그인 완료",
                                        Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                                }else {
                                    progressState = 0
                                    try{
                                        throw task.exception!!
                                    }catch (e : FirebaseAuthInvalidUserException){
                                        Toast.makeText(requireContext(), "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show()
                                    }catch (e: FirebaseAuthInvalidCredentialsException){
                                        Toast.makeText(requireContext(), "이메일 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                                    }catch (e: FirebaseAuthException){
                                        Toast.makeText(requireContext(), e.errorCode, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                    }else {
                        Toast.makeText(
                            requireContext(),
                            "아이디 또는 비밀번호를 입력해주세요",
                            Toast.LENGTH_SHORT).show()
                    } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = colorResource(R.color.mint))
            ){
                Text("LOGIN")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button( // 회원가입
                onClick = { onNavigate(R.id.action_loginFragment_to_mainFragment) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White)
            ) {
                Text(text = "JOIN")
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (progressState == 1){
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = colorResource(R.color.mint),
                    strokeWidth = Dp(value = 4F)
                )
            }
        }
    }

    override fun onStart() { // 자동 로그인
        super.onStart()
        val currentUser = auth?.currentUser
        if (currentUser != null){
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}