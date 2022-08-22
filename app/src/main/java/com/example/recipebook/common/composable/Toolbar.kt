package com.example.recipebook.common.composable

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.recipebook.common.theme.LightFont
import com.example.recipebook.common.theme.Navy
import com.example.recipebook.common.theme.White

@Composable
fun Toolbar(
    @StringRes title: Int,
    icon: ImageVector,
    action: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                fontFamily = LightFont
            )
        },
        navigationIcon = {
            IconButton(
                onClick = action
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "")
            }
        },
        backgroundColor = Navy,
        contentColor = White
    )
}