package com.example.sharerecipy.common.composable

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharerecipy.common.theme.Black
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.common.theme.White
import com.example.sharerecipy.R.string as AppText

@Composable
fun Dialog(
    @StringRes title: Int,
    content: String,
    titleColor: Color,
    icon: ImageVector,
    confirmAction: () -> Unit,
    disMissAction: () -> Unit
) {
    AlertDialog(
        backgroundColor = White,
        onDismissRequest = disMissAction,
        title = {
            Row {
                Icon(
                    icon,
                    contentDescription = "",
                    tint = Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(title),
                    color = titleColor
                )
            }
        },
        text = {
            Text(
                text = content,
                color = Black
            )
        },
        confirmButton = {
            DialogConfirmButton(
                AppText.OK,
                action = confirmAction,
                LightOrange,
                White
            )
        }
    )
}