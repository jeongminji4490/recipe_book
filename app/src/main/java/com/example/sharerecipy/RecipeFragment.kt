package com.example.sharerecipy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.findNavController
import com.example.sharerecipy.screens.recipe.ViewModel
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class RecipeFragment(private val serialNum: Int) : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                MaterialTheme {
//                    RecipeView(
//                        viewModel = hiltViewModel()
//                    )
//                }
//            }
//        }
//    }
//
//    @Composable
//    fun RecipeView(viewModel: ViewModel) {
//        var progressState by remember { mutableStateOf(1) } // 진행바
//        val list = viewModel.infos.observeAsState().value
//
//        list?.let {
//            LazyColumn {
//                items(it.list.recipeInfo) { item ->
//                    MainContent(item, onNavigate = { dest -> findNavController().navigate(dest) })
//                    //CardView(item, onNavigate = { dest -> findNavController().navigate(dest) } )
//                }
//            }
//        }
//
////        if (progressState == 1) {
////            Box(
////                contentAlignment = Alignment.Center, // you apply alignment to all children
////                modifier = Modifier.fillMaxSize()
////            ) {
////                CircularProgressIndicator( // 서버와 통신이 완료될 때까지 보여줄 진행바
////                    modifier = Modifier
////                        .padding(16.dp)
////                        .align(Alignment.Center),
////                    color = Color.Black,
////                    strokeWidth = Dp(value = 4F)
////                )
////            }
////        }
//    }
//
//    @Composable
//    fun MainContent(data: RecipeInfo, onNavigate: (Int) -> Unit) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .padding(20.dp), // Column 안에서 백그라운드 색 설정하면 스캐폴드에서 지정한 배경색 적용 X
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text( // 레시피명
//                text = data.name,
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold
//            )
//            // 메뉴얼
//            Text(
//                text = data.manual1,
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Light
//            )
//        }
//    }
//}