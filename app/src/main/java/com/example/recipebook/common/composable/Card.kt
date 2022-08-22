package com.example.recipebook.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebook.common.theme.*

@Composable
fun MenuCardComposable(
    @StringRes text: Int,
    icon: ImageVector,
    action: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable(onClick = action),
        backgroundColor = Navy,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(text),
                color = Beige,
                fontSize = 20.sp,
                fontFamily = BoldFont
            )
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp),
                tint = Beige)
        }
    }
}

@Composable
fun ArticleCardComposable(
    @StringRes text: Int,
    uri: String
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            .clickable { uriHandler.openUri(uri) },
        border = BorderStroke(2.dp, Navy),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Beige
    ) {
        Text(
            text = stringResource(text),
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            color = Navy,
            fontSize = 17.sp,
            fontFamily = BoldFont
        )
    }
}