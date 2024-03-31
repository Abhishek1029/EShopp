package com.futurecoder.eshopp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import com.futurecoder.eshopp.data.BottomNavigationItem
import com.futurecoder.eshopp.data.OnBoardingData
import com.futurecoder.eshopp.utils.EShoppConstants.CART
import com.futurecoder.eshopp.utils.EShoppConstants.DASHBOARD
import com.futurecoder.eshopp.utils.EShoppConstants.WISHLIST
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.R.drawable as AppDrawable

object EShoppHelper {
    fun getListOfPlayers(): List<String> {
        return arrayListOf<String>(
            "Virendra Sehwag",
            "Sachin Tendulkar",
            "Gautam Gambhir",
            "Virat Kohli",
            "Yuvraj Singh",
            "MS Dhoni",
            "Yusuf Pathan",
            "Harbhajan Singh",
            "Munaf Patel",
            "Zaheer Khan",
            "S. Sreesanth"
        )
    }

    fun getOnBoardingData(): List<OnBoardingData> {
        return listOf(
            OnBoardingData(
                AppDrawable.purchase_online,
                AppString.order,
                AppString.visit_online_shop
            ),
            OnBoardingData(
                AppDrawable.pay_online,
                AppString.pay,
                AppString.make_your_purchase
            ),
            OnBoardingData(
                AppDrawable.online_delivery,
                AppString.delivery,
                AppString.estimated_delivery
            )
        )
    }

    fun getSliderImages(): List<Int> {
        return listOf(
            AppDrawable.slider_image_1,
            AppDrawable.slider_image_2
        )
    }

    fun getNavBarItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                AppString.home,
                DASHBOARD,
                Icons.Filled.Home,
                Icons.Outlined.Home
            ),
            BottomNavigationItem(
                AppString.wishlist,
                WISHLIST,
                Icons.Filled.Favorite,
                Icons.Outlined.FavoriteBorder
            ),
            BottomNavigationItem(
                AppString.cart,
                CART,
                Icons.Filled.ShoppingCart,
                Icons.Outlined.ShoppingCart
            )
        )
    }
}