package com.example.sharerecipy.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Tab
import androidx.compose.material.icons.filled.Tag
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.sharerecipy.common.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComposable(
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        border = BorderStroke(
            width = ChipDefaults.OutlinedBorderSize,
            color = Beige
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = Navy,
            contentColor = Beige
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Tag,
                contentDescription = ""
            )
        }
    ) {
        Text(
            text = stringResource(text),
            fontFamily = LightFont
        )
    }
}