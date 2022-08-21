package com.example.sharerecipy.common.composable

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ManualImageComposable(
    imageModel: Any?
) {
    GlideImage(
        imageModel = imageModel,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(15.dp)
    )
}