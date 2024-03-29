package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    @StringRes labelText: Int
) {
    var queryString by remember {
        mutableStateOf("")
    }
    TextField(value = queryString, onValueChange = {
        queryString = it
    },
        modifier = modifier, label = {
            CustomText(text = labelText)
        })
}

@Composable
fun CustomTextFieldWithLeadingIcon(
    modifier: Modifier = Modifier,
    @StringRes labelText: Int,
    leadingIcon: @Composable() (() -> Unit)?
) {
    var queryString by remember {
        mutableStateOf("")
    }
    TextField(
        value = queryString, onValueChange = {
            queryString = it
        },
        modifier = modifier, placeholder = {
            CustomText(text = labelText, fontSize = 15.sp)
        }, leadingIcon = leadingIcon
    )
}