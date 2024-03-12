package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes buttonText: Int,
    btnTextFontSize: TextUnit = 16.sp,
    onButtonClick: () -> Unit
) {
    Button(onClick = {
        onButtonClick()
    }, modifier = modifier) {
        CustomText(text = buttonText, fontSize = btnTextFontSize)
    }
}