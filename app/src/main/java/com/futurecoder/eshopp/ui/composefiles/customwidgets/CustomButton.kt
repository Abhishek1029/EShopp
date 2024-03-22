package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes buttonText: Int,
    btnTextFontSize: TextUnit = 16.sp,
    btnShape: Shape = ButtonDefaults.shape,
    btnTextColor: Color = Color.Black,
    onButtonClick: () -> Unit
) {
    Button(onClick = {
        onButtonClick()
    }, modifier = modifier, shape = btnShape) {
        CustomText(text = buttonText, fontSize = btnTextFontSize, textColor = btnTextColor)
    }
}