package com.example.recipebook.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tag
import androidx.compose.runtime.Composable
import com.example.recipebook.common.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComposable(
    text: String,
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
            text = text,
            fontFamily = LightFont
        )
    }
}