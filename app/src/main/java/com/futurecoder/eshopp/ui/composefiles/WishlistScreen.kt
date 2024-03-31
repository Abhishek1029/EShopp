package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.runtime.Composable
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.R.drawable as AppDrawable

@Composable
fun WishlistScreen(
    navigateToDashboard: (String) -> Unit
) {
    //Wishlist()
    EmptyDataScreen(
        AppString.empty_wishlist,
        AppString.explore_more_and_shortlist_item,
        AppString.start_shopping,
        AppDrawable.no_data_wishlist,
        navigateToDashboard = navigateToDashboard
    )
}

@Composable
fun Wishlist() {

}