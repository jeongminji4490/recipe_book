package com.example.sharerecipy.common.composable

import android.graphics.drawable.Icon
import android.text.style.BackgroundColorSpan
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharerecipy.R
import com.example.sharerecipy.SIGNUP_SCREEN

// 기본 버튼
@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.lightOrange),
            contentColor = Color.White)
    ) {
        Text(
            text = stringResource(text),
            fontSize = 17.sp
        )
    }
}

// 배경 & 텍스트를 원하는 색상으로 지정할 수 았는 버튼
@Composable
fun ColorButton(
    @StringRes text: Int,
    modifier: Modifier,
    @ColorRes backgroundColor: Int,
    @ColorRes contentColor: Int,
    action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(backgroundColor),
            contentColor = colorResource(contentColor))
    ) {
        Text(
            text = stringResource(text),
            fontSize = 17.sp
        )
    }
}

@Composable
fun BasicOutlinedButton(
    @StringRes text: Int,
    @ColorRes backgroundColor: Int,
    @ColorRes contentColor: Int,
    @ColorRes borderColor: Int,
    modifier: Modifier,
    action: () -> Unit
){
    OutlinedButton( // 방법
        onClick = action,
        modifier = Modifier,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, colorResource(borderColor)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(backgroundColor),
            contentColor = colorResource(contentColor)
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun IconOutlinedButton(
    @StringRes text: Int,
    icon: ImageVector,
    description: String,
    modifier: Modifier,
    action: () -> Unit) {
    OutlinedButton(
        onClick = action,
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.lightOrange),
            contentColor = Color.White
        ),
    ) {
        Text(
            text = stringResource(text),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = icon,
            modifier = Modifier.padding(2.dp),
            contentDescription = description,
            tint = Color.White
        )
    }
}

@Composable
fun DialogConfirmButton(
    @StringRes text: Int,
    action: () -> Unit,
    @ColorRes backgroundColor: Int,
    @ColorRes contentColor: Int
) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(backgroundColor),
            contentColor = colorResource(contentColor)
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(
    @StringRes text: Int,
    action: () -> Unit,
    @ColorRes backgroundColor: Int,
    @ColorRes contentColor: Int
) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(backgroundColor),
            contentColor = colorResource(contentColor)
        )
    ) {
        Text(text = stringResource(text))
    }
}