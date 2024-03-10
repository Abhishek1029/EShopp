package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun CustomOutlinedTextBox(
    modifier: Modifier = Modifier,
    @StringRes textLabel: Int,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier,
        label = {
            Text(text = stringResource(id = textLabel))
        }
    )
}

@Composable
fun CustomOutlinedTextFieldWithLeadingIcon(
    modifier: Modifier = Modifier,
    @StringRes placeholderText: Int,
    enabled: Boolean = true,
    readOnly : Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    leadingIcon: @Composable (() -> Unit)?
) {
    var queryString by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = queryString, onValueChange = {
            queryString = it
        },
        modifier = modifier, placeholder = {
            CustomText(text = placeholderText, fontSize = 15.sp)
        }, leadingIcon = leadingIcon, enabled = enabled, readOnly = readOnly, colors = colors
    )
}