package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextFieldWithLabel(
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
fun CustomTextFieldWithPlaceholder(
    modifier: Modifier = Modifier,
    receivedText: String = "",
    @StringRes placeholderText: Int,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = Int.MAX_VALUE,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = receivedText, onValueChange = {
            if (it.length <= maxLength) {
                onTextChange(it)
            }
        },
        modifier = modifier, colors = colors, placeholder = {
            CustomText(text = placeholderText)
        }, keyboardOptions = keyboardOptions
    )
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