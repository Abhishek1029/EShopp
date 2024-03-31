@file:OptIn(ExperimentalGlideComposeApi::class)

package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.ui.composefiles.customwidgets.CustomText
import com.futurecoder.eshopp.viewmodels.ProductDetailViewModel
import com.futurecoder.eshopp.R.string as AppString

@Composable
fun ProductDetailScreen(
    productDetailViewModel: ProductDetailViewModel = hiltViewModel()
) {
    val product by productDetailViewModel.productStateFlow.collectAsStateWithLifecycle()
    ProductDetail(product)
}

@Composable
fun ProductDetail(product: Product?) {
    if (product == null)
        return
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (productImage, viewSimilar, ratingBox, productTitle, productDescription, productMRP, wishlistBtn, cartBtn) = createRefs()
        GlideImage(
            model = product.image, contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxHeight(.5f)
                .background(Color.Blue)
                .constrainAs(productImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, contentScale = ContentScale.FillBounds
        )

        ElevatedCard(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .constrainAs(viewSimilar) {
                    start.linkTo(parent.start, 5.dp)
                    bottom.linkTo(productImage.bottom, 5.dp)
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
                    text = AppString.view_similar,
                    textColor = Color.White,
                    fontSize = 11.sp,
                    textStyle = FontWeight.Normal,
                )
            }
        }

        ElevatedCard(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .constrainAs(ratingBox) {
                    end.linkTo(parent.end, 5.dp)
                    bottom.linkTo(productImage.bottom, 5.dp)
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

        CustomText(
            text = product.title.orEmpty(),
            modifier = Modifier
                .padding(start = 5.dp)
                .constrainAs(productTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(productImage.bottom, 5.dp)
                },
            textColor = Color.Black,
            fontSize = 16.sp,
            textStyle = FontWeight.Bold,
            maxLines = 1
        )

        CustomText(
            text = product.description.orEmpty(),
            modifier = Modifier
                .padding(start = 5.dp)
                .constrainAs(productDescription) {
                    start.linkTo(parent.start)
                    top.linkTo(productTitle.bottom, 3.dp)
                },
            textColor = Color.LightGray,
            fontSize = 13.sp,
            textStyle = FontWeight.Normal,
            maxLines = 2
        )

        CustomText(
            text = " ${stringResource(id = AppString.Rs)}${product.price?.toString().orEmpty()}",
            modifier = Modifier
                .padding(start = 5.dp)
                .constrainAs(productMRP) {
                    start.linkTo(parent.start)
                    top.linkTo(productDescription.bottom, 3.dp)
                },
            textColor = Color.Black,
            fontSize = 15.sp,
            textStyle = FontWeight.Bold,
            maxLines = 1
        )

        OutlinedButton(onClick = {},
            modifier = Modifier
                .width(150.dp)
                .height(40.dp)
                .constrainAs(wishlistBtn) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 5.dp)
            }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite Icon"
                )
                CustomText(text = AppString.wishlist,
                    modifier = Modifier.padding(start = 7.dp),
                    fontSize = 13.sp)
            }
        }


        OutlinedButton(onClick = {},
            modifier = Modifier
                .width(150.dp)
                .height(40.dp)
                .constrainAs(cartBtn) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 5.dp)
            }) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Favourite Icon",
                )
                CustomText(text = AppString.add_to_cart,
                    modifier = Modifier.padding(start = 7.dp),
                    fontSize = 13.sp)
            }
        }
    }
}