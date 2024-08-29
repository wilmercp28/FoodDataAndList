package com.wilmer.fooddataandlist.utilities

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.sp
import com.wilmer.fooddataandlist.data.model.Sizes

object FontUtilities {

    @Composable
    fun ScaledTitleLargeText(text: String, size: Sizes) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp

        var scaledFontSize = when {
            screenWidth > 600 -> 28  // For tablets or large screens
            screenWidth > 400 -> 24  // For medium screens
            else -> 20        // For small screens
        }

        scaledFontSize += when (size){
            Sizes.EXTRASMAll -> 0
            Sizes.SMALL -> 4
            Sizes.MEDIUM -> 8
             Sizes.LARGE -> 28
            Sizes.EXTRALARGE -> 36
            Sizes.EXTRALARGEPLUS -> 44
        }

        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = scaledFontSize.sp)
        )
    }
}