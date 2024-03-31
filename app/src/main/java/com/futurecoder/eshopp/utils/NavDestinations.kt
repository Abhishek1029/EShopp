package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.utils.EShoppConstants.ADDRESS
import com.futurecoder.eshopp.utils.EShoppConstants.ADD_ADDRESS
import com.futurecoder.eshopp.utils.EShoppConstants.CART
import com.futurecoder.eshopp.utils.EShoppConstants.DASHBOARD
import com.futurecoder.eshopp.utils.EShoppConstants.ON_BOARDING
import com.futurecoder.eshopp.utils.EShoppConstants.PRODUCT_DETAIL
import com.futurecoder.eshopp.utils.EShoppConstants.PROFILE
import com.futurecoder.eshopp.utils.EShoppConstants.SEARCH
import com.futurecoder.eshopp.utils.EShoppConstants.SIGN_UP
import com.futurecoder.eshopp.utils.EShoppConstants.SPLASH
import com.futurecoder.eshopp.utils.EShoppConstants.WISHLIST

object SplashDestination {
    const val ROUTE = SPLASH
}

object OnBoardingDestination {
    const val ROUTE = ON_BOARDING
}

object DashboardDestination {
    const val ROUTE = DASHBOARD
}

object SearchDestination {
    const val ROUTE = SEARCH
}

object ProductDetailDestination {
    const val ROUTE = PRODUCT_DETAIL
    const val ARGS = "{productId}"
}

object SignupDestination {
    const val ROUTE = SIGN_UP
}

object ProfileDestination {
    const val ROUTE = PROFILE
}

object AddAddressDestination {
    const val ROUTE = ADD_ADDRESS
    const val ARGS = "{addressId}"
}

object AddressDestination {
    const val ROUTE = ADDRESS
}

object WishlistDestination {
    const val ROUTE = WISHLIST
}

object CartDestination {
    const val ROUTE = CART
}