package com.futurecoder.eshopp.ui.composefiles

import androidx.compose.runtime.Composable
import com.futurecoder.eshopp.R.string as AppString
import com.futurecoder.eshopp.R.drawable as AppDrawable

@Composable
fun CartScreen() {
    EmptyDataScreen(
        AppString.empty_cart,
        AppString.add_items_to_get_started,
        AppString.start_shopping,
        AppDrawable.no_data_cart
    )
}