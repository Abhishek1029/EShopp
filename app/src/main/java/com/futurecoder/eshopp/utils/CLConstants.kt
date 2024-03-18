package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.BuildConfig

object CLConstants {
    const val DASHBOARD = "dashboard"
    const val SEARCH = "search"
    const val SPLASH = "splash"
    const val SIGN_UP = "sign_up"
    const val PROFILE = "profile"
    const val ADD_ADDRESS = "add_address"
    const val ADDRESS = "address"
    const val FIRE_STORE_USERS_COLLECTION = "users"
    const val FIRE_STORE_ADDRESSES_COLLECTION = "addresses"
    const val APP_VERSION = BuildConfig.VERSION_NAME
    const val MIN_PASS_LENGTH = 6
    const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
}