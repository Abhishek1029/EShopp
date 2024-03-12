package com.futurecoder.eshopp.ui.composefiles

import android.content.Context
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.futurecoder.eshopp.R

fun returnAnnotatedString(
    context: Context
): AnnotatedString {
    return buildAnnotatedString {
        context.getString(R.string.do_not_have_account)
        withStyle(
            style = SpanStyle(color = Color.Red)
        ) {
            context.getString(R.string.sign_up)
        }
    }
}

/*
@Composable
fun CustomClickableText() {
    val context = LocalContext.current
    ClickableText(text = returnAnnotatedString(context), onClick =)
}*/
