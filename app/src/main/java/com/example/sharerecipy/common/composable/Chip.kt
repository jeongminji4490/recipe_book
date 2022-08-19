package com.example.sharerecipy.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.sharerecipy.common.theme.Black
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.common.theme.White

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComposable(
    @StringRes text: Int,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            LightOrange
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = White,
            contentColor = LightOrange
        ),
        leadingIcon = {
            Icon(
                icon,
                contentDescription = ""
            )
        }
    ) {
        Text(stringResource(text))
    }
}