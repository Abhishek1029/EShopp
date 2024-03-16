@file:OptIn(ExperimentalMaterial3Api::class)

package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme

@Composable
fun AddAddressScreen() {
    AddAddress()
}

@Composable
fun AddAddress() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ElevatedCard(
            onClick = {
            }, modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 3.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            CustomText(
                text = AppString.fetch_current_address,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                textStyle = FontWeight.Bold,
                textAlignment = TextAlign.Center
            )
        }

        CustomText(
            text = AppString.or,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            textStyle = FontWeight.Bold,
            textAlignment = TextAlign.Center
        )

        ElevatedCard(
            onClick = {
            }, modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 15.dp, top = 3.dp, end = 15.dp, bottom = 3.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 10.dp
            )
        ) {
            CustomText(
                text = AppString.add_address_manually,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                textStyle = FontWeight.Bold,
                textAlignment = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun AddAddressPreview() {
    EShoppTheme {
        AddAddressScreen()
    }
}