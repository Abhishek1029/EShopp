package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.R

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
    readOnly: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    onTextFieldValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)?
) {
    var queryString by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = queryString, onValueChange = {
            queryString = it
            onTextFieldValueChange(queryString)
        },
        modifier = modifier, placeholder = {
            CustomText(text = placeholderText, fontSize = 15.sp)
        }, leadingIcon = leadingIcon, enabled = enabled, readOnly = readOnly, colors = colors
    )
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    @StringRes placeholderText: Int,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    onTextFieldValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)?
) {
    var queryString by remember {
        mutableStateOf("")
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    val icon = if (isVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off
    val transformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        value = queryString,
        onValueChange = {
            queryString = it
            onTextFieldValueChange(queryString)
        },
        modifier = modifier,
        placeholder = {
            CustomText(text = placeholderText, fontSize = 15.sp)
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(onClick = {
                isVisible = !isVisible
            }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Password Visibility"
                )
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        colors = colors,
        visualTransformation = transformation
    )
}