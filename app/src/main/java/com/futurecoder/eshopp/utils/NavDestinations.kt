package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.utils.EShoppConstants.ADDRESS
import com.futurecoder.eshopp.utils.EShoppConstants.ADD_ADDRESS
import com.futurecoder.eshopp.utils.EShoppConstants.DASHBOARD
import com.futurecoder.eshopp.utils.EShoppConstants.ON_BOARDING
import com.futurecoder.eshopp.utils.EShoppConstants.PROFILE
import com.futurecoder.eshopp.utils.EShoppConstants.SEARCH
import com.futurecoder.eshopp.utils.EShoppConstants.SIGN_UP
import com.futurecoder.eshopp.utils.EShoppConstants.SPLASH

object SplashDestination{
    const val route = SPLASH
}

object OnBoardingDestination{
    const val route = ON_BOARDING
}
object DashboardDestination{
    const val route = DASHBOARD
}

object SearchDestination{
    const val route = SEARCH
}

object SignupDestination{
    const val route = SIGN_UP
}

object ProfileDestination{
    const val route = PROFILE
}
object AddAddressDestination{
    const val route = ADD_ADDRESS
    var args = "{addressId}"
}

object AddressDestination{
    const val route = ADDRESS
}