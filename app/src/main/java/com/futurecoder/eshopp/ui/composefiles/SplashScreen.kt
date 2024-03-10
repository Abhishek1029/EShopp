package com.futurecoder.eshopp.ui.composefiles

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    @DrawableRes splashIcon: Int,
    navigateToDashboard: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navigateToDashboard()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = splashIcon),
            contentDescription = stringResource(id = R.string.splash_icon),
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    EShoppTheme {
        SplashScreen(splashIcon = R.drawable.splash_logo) {

        }
    }
}