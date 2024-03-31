package com.futurecoder.eshopp.ui.composefiles

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomButton
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.R.drawable as AppDrawable
import com.futurecoder.eshopp.R.string as AppString

@Composable
fun EmptyDataScreen(
    @StringRes emptyDataTitle: Int,
    @StringRes emptyDataSubtitle: Int,
    @StringRes buttonText: Int,
    @DrawableRes emptyDataDrawable: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = emptyDataDrawable),
            contentDescription = "Empty Screen Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            text = stringResource(id = emptyDataTitle),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            textStyle = FontWeight.Bold,
            textAlignment = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomText(
            text = stringResource(id = emptyDataSubtitle),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
            textAlignment = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(buttonText = buttonText,
            btnTextColor = Color.White) {

        }
    }
}

@Preview
@Composable
private fun EmptyDataScreenPreview() {
    EShoppTheme {
        EmptyDataScreen(
            AppString.empty_wishlist,
            AppString.explore_more_and_shortlist_item,
            AppString.start_shopping,
            AppDrawable.no_data_wishlist
        )
    }
}