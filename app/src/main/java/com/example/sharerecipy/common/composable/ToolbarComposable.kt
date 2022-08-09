package com.example.sharerecipy.common.composable

import android.widget.Toolbar
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import com.example.sharerecipy.R

@Composable
fun Toolbar(@StringRes title: Int, icon: ImageVector, action: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                fontFamily = FontFamily.Serif
            )
        },
        navigationIcon = { // 로그아웃 버튼(로그아웃 확인 다이얼로그?)
            IconButton(
                onClick = action
            ) { Icon(icon,"") }
        },
        backgroundColor = colorResource(R.color.lightOrange),
        contentColor = Color.White
    )
}