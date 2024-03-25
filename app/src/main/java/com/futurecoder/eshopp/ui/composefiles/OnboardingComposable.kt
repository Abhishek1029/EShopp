package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.utils.EShoppHelper.getOnBoardingData
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch
import com.futurecoder.eshopp.R.string as AppString

@Composable
fun OnboardingScreen(
    navigateToNextScreen: () -> Unit
) {
    Onboarding(navigateToNextScreen = navigateToNextScreen)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboarding(
    navigateToNextScreen: () -> Unit
) {
    val onBoardingList = getOnBoardingData()
    val pagerState = rememberPagerState {
        onBoardingList.size
    }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxHeight(.85f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = onBoardingList[index].onBoardingImages),
                    contentDescription = "OnBoarding Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )

                CustomText(
                    text = onBoardingList[index].onBoardingHeading,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp,
                    textStyle = FontWeight.Bold,
                    textAlignment = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomText(
                    text = onBoardingList[index].onBoardingSubHeading,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    textAlignment = TextAlign.Center
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                text = AppString.previous,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                        }
                    },
                fontSize = 18.sp,
                textAlignment = TextAlign.Start
            )

            HorizontalPagerIndicator(pagerState = pagerState, pageCount = onBoardingList.size)

            CustomText(
                text = if (pagerState.currentPage == onBoardingList.size - 1) AppString.finish else AppString.next,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        coroutineScope.launch {
                            if (pagerState.currentPage == onBoardingList.size - 1) {
                                navigateToNextScreen()
                            } else {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                fontSize = 18.sp,
                textColor = if (pagerState.currentPage == onBoardingList.size - 1) Color.Red else Color.Black
            )
        }
    }
}