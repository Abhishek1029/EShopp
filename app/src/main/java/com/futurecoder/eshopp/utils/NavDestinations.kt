package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.utils.CLConstants.ADDRESS
import com.futurecoder.eshopp.utils.CLConstants.ADD_ADDRESS
import com.futurecoder.eshopp.utils.CLConstants.DASHBOARD
import com.futurecoder.eshopp.utils.CLConstants.PROFILE
import com.futurecoder.eshopp.utils.CLConstants.SEARCH
import com.futurecoder.eshopp.utils.CLConstants.SIGN_UP
import com.futurecoder.eshopp.utils.CLConstants.SPLASH

object SplashDestination{
    const val route = SPLASH
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