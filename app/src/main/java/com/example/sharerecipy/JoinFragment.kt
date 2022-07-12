package com.example.sharerecipy

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JoinFragment : Fragment() {

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
                    joinView()
                }
            }
        }
    }

    @Composable
    fun joinView(){
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
        var email by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        var confirmPw by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "JOIN",
                textAlign = TextAlign.Left,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = colorResource(R.color.crimsonRed),
                    unfocusedBorderColor = Color.White
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = pw,
                onValueChange = { pw = it },
                label = { Text("Password", color = Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = colorResource(R.color.crimsonRed),
                    unfocusedBorderColor = Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPw,
                onValueChange = { confirmPw = it },
                label = { Text("confirm Password", color = Color.White) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = colorResource(R.color.crimsonRed),
                    unfocusedBorderColor = Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { join(email, pw, confirmPw) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor =  colorResource(R.color.crimsonRed))
            ) {
                Text(text = "OK")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { onNavigate(R.id.action_joinFragment_to_loginFragment) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White)
            ) {
                Text(text = "CANCEL")
            }
        }

    }

    // firestore 보안규칙 설정
    private fun join(email: String, passWord: String, confirmPassword: String){
        val db = Firebase.firestore
        val users = db.collection("user")

        if (confirmPassword!=passWord){
            Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            if (email.isNotEmpty() && passWord.isNotEmpty()){
                auth?.createUserWithEmailAndPassword(email, passWord)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = hashMapOf(
                                "email" to email,
                                "nickname" to "신규회원",
                                "password" to passWord
                            )
                            users.document(email).set(user)
                                .addOnSuccessListener { it ->
                                    Log.i(TAG, "DocumentSnapshot added with ID: $it")
                                }.addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                            Toast.makeText(requireContext(), "가입 완료", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_joinFragment_to_loginFragment)
                        }else {
                            try {
                                throw task.exception!!
                            }catch (e: FirebaseAuthUserCollisionException){
                                Toast.makeText(requireContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthWeakPasswordException){
                                Toast.makeText(requireContext(), "비밀번호는 최소 6자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                            }
                            catch (e: FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(requireContext(), "유효하지 않은 이메일 형식입니다.", Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthException){
                                Toast.makeText(requireContext(), e.errorCode, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }else {
                Toast.makeText(requireContext(), "모든 입력란을 채워주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        const val TAG = "FIRE_STORE"
    }
}