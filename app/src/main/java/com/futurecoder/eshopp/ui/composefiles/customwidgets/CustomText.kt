package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    fontSize: TextUnit = 12.sp,
    textStyle: FontWeight = FontWeight.Normal
) {
    Text(
        text = stringResource(id = text),
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = textStyle
    )
}