@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)

package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.futurecoder.eshopp.R
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomOutlinedTextFieldWithLeadingIcon
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.ui.theme.EShoppTheme
import com.futurecoder.eshopp.utils.EShoppConstants.BANNER_AUTO_SCROLL_DELAY_TIME
import com.futurecoder.eshopp.utils.EShoppConstants.PRODUCT_COLS_PER_ROW
import com.futurecoder.eshopp.utils.EShoppHelper.getSliderImages
import com.futurecoder.eshopp.utils.ProductDetailDestination
import com.futurecoder.eshopp.viewmodels.DashboardViewModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    onProfileIconClicked: (Boolean, Boolean) -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    openDetailScreen: (String, Int) -> Unit,
    onSearchBarClick: () -> Unit
) {
    val sliderImages = getSliderImages()
    val pagerState = rememberPagerState {
        sliderImages.size
    }
    val productList by dashboardViewModel.productsStateFlow.collectAsStateWithLifecycle()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (locationText, profileImage, searchBox, banner, bannerIndicator, productsLVG) = createRefs()
        val startGuideline = createGuidelineFromStart(.01f)
        val endGuideline = createGuidelineFromEnd(.01f)
        val bottomGuideline = createGuidelineFromBottom(.01f)
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(PRODUCT_COLS_PER_ROW),
            modifier = Modifier
                .background(Color.White)
                .constrainAs(productsLVG) {
                    top.linkTo(bannerIndicator.bottom, 10.dp)
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    bottom.linkTo(bottomGuideline)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(productList) { product ->
                ProductItem(product, openDetailScreen)
            }
        }

        LaunchedEffect(key1 = pagerState.currentPage) {
            delay(BANNER_AUTO_SCROLL_DELAY_TIME)
            if (pagerState.currentPage == sliderImages.size - 1)
                pagerState.scrollToPage(0)
            else
                pagerState.scrollToPage(pagerState.currentPage + 1)
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    openDetailScreen: (String, Int) -> Unit
) {
    //val bgColor = if (index % 2 == 0) Color.Magenta else Color.Cyan
    ElevatedCard(
        onClick = {
            openDetailScreen(
                ProductDetailDestination.route, product.id ?: 0
            )
        },
        modifier = Modifier
            .height(250.dp)
            .background(Color.White),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ), shape = RectangleShape, elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 10.dp
        )
    ) {
        Column {
            ConstraintLayout(
                modifier = Modifier.height(150.dp),
            ) {
                val (glideImage, ratingRow) = createRefs()
                GlideImage(
                    model = product.image, contentDescription = "Product Image",
                    modifier = Modifier
                        .height(150.dp)
                        .constrainAs(glideImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    contentScale = ContentScale.FillBounds
                )
                ElevatedCard(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp)
                        .constrainAs(ratingRow) {
                            start.linkTo(parent.start, 5.dp)
                            bottom.linkTo(parent.bottom, 5.dp)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = product.rating?.rate?.toString().orEmpty(),
                            textColor = Color.White,
                            fontSize = 11.sp,
                            textStyle = FontWeight.Normal,
                            maxLines = 1
                        )

                        Divider(
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight(.6f),
                            thickness = 1.dp,
                            color = Color.White
                        )

                        CustomText(
                            text = product.rating?.count?.toString().orEmpty(),
                            textColor = Color.White,
                            fontSize = 11.sp,
                            textStyle = FontWeight.Normal,
                            maxLines = 1
                        )
                    }
                }
            }
            CustomText(
                text = product.title.orEmpty(),
                modifier = Modifier.padding(start = 3.dp, top = 3.dp),
                textColor = Color.Black,
                fontSize = 14.sp,
                textStyle = FontWeight.Bold,
                maxLines = 1
            )
            CustomText(
                text = product.description.orEmpty(),
                modifier = Modifier.padding(start = 3.dp, top = 3.dp),
                textColor = Color.Black,
                fontSize = 11.sp,
                textStyle = FontWeight.Normal,
                maxLines = 1
            )

            CustomText(
                text = "${stringResource(id = AppString.Rs)}${
                    product.price?.toString().orEmpty()
                }",
                modifier = Modifier.padding(start = 3.dp, top = 3.dp),
                textColor = Color.Black,
                fontSize = 13.sp,
                textStyle = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EShoppTheme {
        DashboardScreen(onProfileIconClicked = { _, _ ->

        }, openDetailScreen = { _, _ ->

        }) {

        }
    }
}