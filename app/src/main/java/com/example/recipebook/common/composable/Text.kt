package com.example.recipebook.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.common.theme.*

@Composable
fun CategoryTextComposable(
    @StringRes text: Int
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            Icons.Filled.Circle,
            "",
            Modifier
                .size(15.dp)
                .padding(top = 8.dp),
            tint = Navy
        )
        Text(
            text = stringResource(text),
            textAlign = TextAlign.Left,
            color = Navy,
            fontSize = 20.sp,
            fontFamily = BoldFont
        )
    }
}

@Composable
fun ManualTextComposable(
    text: String?
) {
    Text(
        text = text ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        textAlign = TextAlign.Left,
        color = Navy,
        fontSize = 16.sp,
        fontFamily = LightFont
    )
}