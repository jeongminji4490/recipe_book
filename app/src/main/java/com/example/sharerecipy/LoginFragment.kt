package com.example.sharerecipy

import android.app.Activity.RESULT_OK
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
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
                    Greeting()
                }
            }
        }
    }

    @Composable
    fun Greeting() {
        var id by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "MY",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "RECIPE",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            GlideImage(
                imageModel = R.drawable.vegetables,
                modifier = Modifier.height(150.dp).width(210.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = id,
                onValueChange = { id = it },
                label = { Text("ID") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Black
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = pw,
                onValueChange = { pw = it },
                label = { Text(text = "Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                          login(id, pw)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White)
            ){
                Text("LOGIN")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /**/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black)
            ) {
                Text(text = "JOIN")
            }
        }
    }

    private fun join(email: String, passWord: String){
        if (email.isNotEmpty() && passWord.isNotEmpty()){
            auth?.createUserWithEmailAndPassword(email, passWord)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(requireContext(), "가입 완료", Toast.LENGTH_SHORT).show()
                    }else {
                        Log.e("Login", task.result.toString())
                        Toast.makeText(requireContext(), "가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun login(email: String, passWord: String){
        if (email.isNotEmpty() && passWord.isNotEmpty()){
            auth?.signInWithEmailAndPassword(email, passWord)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(requireContext(), "로그인 완료", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }


}