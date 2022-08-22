package com.example.recipebook.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.common.theme.*

@Composable
fun BasicButton(
    @StringRes text: Int,
    modifier: Modifier,
    backgroundColor: Color,
    contentColor: Color,
    action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor)
    ) {
        Text(
            text = stringResource(text),
            fontSize = 17.sp,
            fontFamily = LightFont
        )
    }
}

@Composable
fun BasicOutlinedButton(
    @StringRes text: Int,
    backgroundColor: Color,
    contentColor: Color,
    borderColor: Color,
    modifier: Modifier,
    action: () -> Unit
){
    OutlinedButton( // 방법
        onClick = action,
        modifier = Modifier,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor)
    ) {
        Text(
            text = stringResource(text),
            fontFamily = BoldFont
        )
    }
}

@Composable
fun DialogConfirmButton(
    @StringRes text: Int,
    action: () -> Unit,
    backgroundColor: Color,
    contentColor: Color
) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = null
    ) {
        Text(
            text = stringResource(text),
            fontFamily = BoldFont
        )
    }
}