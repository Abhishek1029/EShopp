package com.futurecoder.eshopp.ui.composefiles.customwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.ui.theme.EShoppTheme

@Composable
fun BasicDialog(
    headingText: String = "",
    subHeadingText: String,
    dialogBgColor: Color = Color.White,
    dismissOnBackPress: Boolean = true,
    dismissOnOutsideClick: Boolean = true,
    positiveButtonText: String = stringResource(id = AppString.yes),
    negativeButtonText: String = stringResource(id = AppString.no),
    positiveButtonTextColor: Color = Color.Black,
    negativeButtonTextColor: Color = Color.Black,
    onDialogDismiss: () -> Unit,
    onNegativeButtonClick: () -> Unit,
    onPositiveButtonClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDialogDismiss()
        }, properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnOutsideClick
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(dialogBgColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            if (headingText.isNotBlank()) {
                CustomText(
                    text = headingText,
                    fontSize = 18.sp,
                    textStyle = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomText(
                text = subHeadingText,
                fontSize = 16.sp,
                textAlignment = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(
                    text = negativeButtonText,
                    modifier = Modifier.clickable {
                        onNegativeButtonClick()
                    },
                    fontSize = 16.sp,
                    textAlignment = TextAlign.Center,
                    textColor = negativeButtonTextColor
                )
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                CustomText(
                    text = positiveButtonText,
                    modifier = Modifier.clickable {
                        onPositiveButtonClick()
                    },
                    fontSize = 16.sp,
                    textAlignment = TextAlign.Center,
                    textColor = positiveButtonTextColor
                )
            }
        }
    }
}

@Preview
@Composable
fun BasicDialogPreview() {
    EShoppTheme {
        BasicDialog(subHeadingText = "Are you sure you want to logout",
            onDialogDismiss = {

            }, onNegativeButtonClick = {

            }) {

        }
    }
}