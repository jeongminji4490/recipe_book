package com.example.sharerecipy

import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class RecipeListFragment : Fragment() {
    //private val viewModel : ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ListView(
                        viewModel = hiltViewModel()
                    )
                }
            }
        }
    }

    @Composable
    fun ListView(viewModel : ViewModel) {
        val progressState by remember { mutableStateOf(1) } // 진행바
        val list = viewModel.list.observeAsState().value

        list?.let {
            LazyColumn {
                items(it.list.recipes) { item ->
                    CardView(item, onNavigate = { dest -> findNavController().navigate(dest) } )
                }
            }
        }
//        if (progressState == 1) {
//            Box(
//                contentAlignment = Alignment.Center, // you apply alignment to all children
//                modifier = Modifier.fillMaxSize()
//            ) {
//                CircularProgressIndicator( // 서버와 통신이 완료될 때까지 보여줄 진행바
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .align(Alignment.Center),
//                    color = Color.Black,
//                    strokeWidth = Dp(value = 4F)
//                )
//            }
//        }
    }

    @Composable
    fun CardView(data: Recipe, onNavigate: (Int) -> Unit) {
        val ingredientDialog = remember { mutableStateOf(false) }
        val pickState = remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            elevation = 10.dp,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { pickState.value = !pickState.value },
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "",
                        tint = if (!pickState.value) Color.LightGray else Color.Red
                    )
                }
                GlideImage( // 레시피 메인이미지
                    imageModel = data.imageUrl,
                    modifier = Modifier
                        .height(150.dp)
                        .width(210.dp)
                        .padding(10.dp)
                )
                Text( // 레시피명
                    text = data.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.padding(5.dp)
                ) {
                    OutlinedButton( // 재료
                        onClick = { ingredientDialog.value = true },
                        modifier = Modifier
                            .padding(5.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text(text = "재료")
                    }
                    OutlinedButton( // 방법
                        onClick = {  },
                        modifier = Modifier
                            .padding(5.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "방법")
                    }
                    //Spacer(modifier = Modifier.width(100.dp))
                }
                if (ingredientDialog.value) {
                    AlertDialog( // 재료를 보여줄 다이얼로그
                        backgroundColor = Color.Black,
                        onDismissRequest = {
                            ingredientDialog.value = false
                        },
                        title = {
                            Row {
                                Icon(
                                    Icons.Filled.OutdoorGrill,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "재료 목록",
                                    color = Color.White)
                            }
                        },
                        text = {
                            Text(
                                text = data.ingredient,
                                color = Color.White)
                        },
                        confirmButton = {
                            Button(
                                onClick = { ingredientDialog.value = false },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black,
                                    contentColor = colorResource(R.color.mint))
                            ) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    }
}