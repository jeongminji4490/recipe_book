package com.example.sharerecipy.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharerecipy.HOME_SCREEN
import com.example.sharerecipy.R
import com.example.sharerecipy.RECIPE_SCREEN
import com.example.sharerecipy.common.theme.LightOrange
import com.example.sharerecipy.common.theme.White

@Composable
fun CardComposable(
    @StringRes text: Int,
    action: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(onClick = action),
        backgroundColor = LightOrange,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(text),
                modifier = Modifier.fillMaxWidth(),
                color = White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}