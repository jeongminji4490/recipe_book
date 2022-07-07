package com.example.sharerecipy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {

    //private val auth : FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                //auth = Firebase.auth
                mainView()
            }
        }
    }

    @Composable
    fun mainView() { // 기본키 : uid ?
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        var email = ""
        var nickname by remember { mutableStateOf("") }
        user?.let {
            email = user.email.toString()
        }
        val docRef = db.collection("user").document(email)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nickname = run {
                        document.data?.get("nickname").toString()
                    }
                    Log.d("Main", "DocumentSnapshot data: ${document.data?.get("nickname")}")
                } else {
                    Log.d("Main", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Main", "get failed with ", exception)
            }
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
                text = email,
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = nickname,
                textAlign = TextAlign.Left,
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}