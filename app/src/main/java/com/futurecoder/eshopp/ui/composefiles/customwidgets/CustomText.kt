package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.ui.composefiles.returnAnnotatedString

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    fontSize: TextUnit = 12.sp,
    textStyle: FontWeight = FontWeight.Normal,
    textAlignment: TextAlign = TextAlign.Start
) {
    Text(
        text = stringResource(id = text),
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = textStyle,
        textAlign = textAlignment
    )
}

@Composable
fun CustomAnnotatedText(
    modifier: Modifier = Modifier,
    annotatedText: AnnotatedString,
    fontSize: TextUnit = 12.sp,
    textStyle: FontWeight = FontWeight.Normal
) {
    Text(
        text = annotatedText,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = textStyle
    )
}