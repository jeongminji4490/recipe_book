package com.example.sharerecipy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    listView()
                }
            }
        }
    }

    @Composable
    fun listView(){
        var progressState by remember { mutableStateOf(1) }
        val keyId = "af2bd97db6b846529d0e"
        val serviceId = "COOKRCP01"
        val dataType = "json"
        val list = mutableStateListOf<Recipe>()

        RetrofitClient.recipeService.getList(keyId, serviceId, dataType)
            .enqueue(object : Callback<RecipeList>{
                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                    if (response.isSuccessful){
                        progressState = 0
                        response.body()?.let {
                            for (i in it.list.recipes.indices){
                                list.add(it.list.recipes[i])
                            }
                        }
                    }else{
                        Log.e("recipe", response.message())
                    }
                }
                override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                    Log.e("recipe", t.toString())
                }
            })
        LazyColumn{
            items(list) { item ->
                cardView(item)
            }
        }
        if (progressState == 1){
            Box(
                contentAlignment = Alignment.Center, // you apply alignment to all children
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    color = colorResource(R.color.mint),
                    strokeWidth = Dp(value = 4F)
                )
            }
        }
    }

    @Composable
    fun cardView(data: Recipe){
        val openDialog = remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            elevation = 10.dp,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = data.imageUrl,
                    modifier = Modifier
                        .height(150.dp)
                        .width(210.dp)
                        .padding(10.dp)
                )
                Text(
                    text = data.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedButton( // 재료
                    onClick = { openDialog.value = true },
                    modifier = Modifier
                        .padding(5.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors( contentColor = Color.Black )
                ) {
                    Text(text = "재료")
                }
                if (openDialog.value){
                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = "재료 목록")
                        },
                        text = {
                            Text(text = data.ingredient)
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    openDialog.value = false
                                }) {
                                Text("확인")
                            }
                        }
                    )
                }
            }
        }
    }
}