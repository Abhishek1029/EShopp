package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomOutlinedTextFieldWithLeadingIcon(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            placeholderText = R.string.search,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(
                    id = R.string.search
                )
            )
        }
    }
}