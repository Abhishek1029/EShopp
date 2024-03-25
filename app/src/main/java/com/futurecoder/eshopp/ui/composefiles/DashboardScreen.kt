package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.utils.EShoppConstants.BANNER_AUTO_SCROLL_DELAY_TIME
import com.futurecoder.eshopp.utils.EShoppHelper.getSliderImages
import com.futurecoder.eshopp.viewmodels.DashboardViewModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    onProfileIconClicked: (Boolean, Boolean) -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    onSearchBarClick: () -> Unit
) {
    val sliderImages = getSliderImages()
    val pagerState = rememberPagerState {
        sliderImages.size
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (locationText, profileImage, searchBox, banner, bannerIndicator) = createRefs()
        val startGuideline = createGuidelineFromStart(.01f)
        val endGuideline = createGuidelineFromEnd(.01f)
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = stringResource(id = R.string.profile),
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    onProfileIconClicked(true, dashboardViewModel.isCurrentUser())
                }
                .constrainAs(
                    profileImage
                ) {
                    end.linkTo(endGuideline)
                    top.linkTo(parent.top)
                }
        )

        CustomText(text = R.string.welcome,
            dynamicString = dashboardViewModel.getCurrentUser()?.displayName ?: "",
            fontSize = 20.sp,
            textStyle = FontWeight.Bold,
            modifier = Modifier.constrainAs(
                locationText
            ) {
                top.linkTo(parent.top)
                start.linkTo(startGuideline)
            })
        CustomOutlinedTextFieldWithLeadingIcon(
            modifier = Modifier
                .height(60.dp)
                .padding(top = 10.dp)
                .clickable {
                    onSearchBarClick()
                }
                .constrainAs(searchBox) {
                    top.linkTo(profileImage.bottom)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    width = Dimension.fillToConstraints
                },
            placeholderText = R.string.search,
            enabled = false,
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledPlaceholderColor = Color.Black,
                disabledLeadingIconColor = Color.Black,
                disabledContainerColor = Color.White
            ), onTextFieldValueChange = {

            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
            modifier = Modifier
                .height(175.dp)
                .constrainAs(banner) {
                    top.linkTo(searchBox.bottom, 10.dp)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    width = Dimension.fillToConstraints
                }
        ) { index ->
            Image(
                painter = painterResource(id = sliderImages[index]),
                contentDescription = "Slider Images",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.FillBounds
            )
        }

        HorizontalPagerIndicator(pagerState = pagerState, pageCount = sliderImages.size,
            modifier = Modifier.constrainAs(bannerIndicator) {
                top.linkTo(banner.bottom, 3.dp)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            })
        LaunchedEffect(key1 = pagerState.currentPage) {
            delay(BANNER_AUTO_SCROLL_DELAY_TIME)
            if (pagerState.currentPage == sliderImages.size - 1)
                pagerState.scrollToPage(0)
            else
                pagerState.scrollToPage(pagerState.currentPage + 1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EShoppTheme {
        DashboardScreen({ _, _ ->

        }) {

        }
    }
}