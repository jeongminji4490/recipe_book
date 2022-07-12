package com.example.sharerecipy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                auth = Firebase.auth
                mainView(onNavigate = { dest -> findNavController().navigate(dest) })
            }
        }
    }

    @Composable
    fun mainView(onNavigate: (Int) -> Unit) {
        val db = Firebase.firestore
        val currentUser = auth.currentUser // 로그인한 사용자
        var email = "" // 이메일
        var nickname by remember { mutableStateOf("") } // 닉네임
        currentUser?.let { info ->
            email = info.email.toString() // 사용자 이메일 가져오기
        }
        val docRef = db.collection("user").document(email)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nickname = run {
                        document.data?.get("nickname").toString() // 사용자 닉네임 가져오기
                    }
                    Log.d("Main", "DocumentSnapshot data: ${document.data?.get("nickname")}")
                } else {
                    Log.d("Main", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Main", "get failed with ", exception)
            }
            Scaffold(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "")
                        },
                        navigationIcon = {
                            IconButton(onClick = { logout() }) {
                                Icon(Icons.Filled.ExitToApp,"")
                            }
                        },
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                }, content = {
                    Column {
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .clickable { },
                            elevation = 10.dp
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(15.dp)
                            ){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = email,
                                    textAlign = TextAlign.Left,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = nickname,
                                    textAlign = TextAlign.Left,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Row (
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    OutlinedButton( // 정보수정
                                        onClick = {  },
                                        modifier = Modifier
                                            .height(40.dp),
                                        border = BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(50),
                                        colors = ButtonDefaults.outlinedButtonColors( contentColor = Color.Black )
                                    ) {
                                        Text(text = "정보수정")
                                    }
                                    OutlinedButton(
                                        // 정보수정
                                        onClick = { },
                                        modifier = Modifier
                                            .height(40.dp),
                                        //border = BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(50),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Black,
                                            contentColor = Color.White
                                        ),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite ,
                                            modifier = Modifier.padding(end = 8.dp),
                                            contentDescription = "favorite",
                                            tint = Color.Red
                                        )
                                        Text(text = "찜목록")
                                    }
                                }
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .clickable { onNavigate(R.id.action_mainFragment_to_recipeListFragment) },
                            backgroundColor = colorResource(R.color.deepPink),
                            elevation = 10.dp,
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(R.drawable.book),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "레시피 보기",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            backgroundColor = colorResource(R.color.babyNavy),
                            elevation = 10.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(R.drawable.recipes),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "내 레시피",
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                                    //textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
//                    LazyColumn{
//                        itemsIndexed(
//                            listOf("apple", "banana", "grape")
//                        ){ index, item ->
//                            recipeCards(item)
//                        }
//                    }
                })
    }

    @Composable
    fun showList(){
        LazyColumn{
            itemsIndexed(
                listOf("apple", "banana", "grape")
            ){ index, item ->  
                recipeCards(item)
            }
        }
    }

    @Composable
    fun recipeCards(title: String){
        Card (
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    private fun logout(){
        auth.signOut()
        findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        Toast.makeText(requireContext(), "logout", Toast.LENGTH_SHORT).show()
    }

}