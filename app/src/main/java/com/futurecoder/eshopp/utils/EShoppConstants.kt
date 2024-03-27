package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.BuildConfig

object EShoppConstants {
    const val DASHBOARD = "dashboard"
    const val SEARCH = "search"
    const val SPLASH = "splash"
    const val ON_BOARDING = "onBoarding"
    const val SIGN_UP = "sign_up"
    const val PROFILE = "profile"
    const val ADD_ADDRESS = "add_address"
    const val ADDRESS = "address"
    const val FIRE_STORE_USERS_COLLECTION = "users"
    const val FIRE_STORE_ADDRESSES_COLLECTION = "addresses"
    const val ESHOP_DATABASE_NAME = "eshop_db"
    const val FAKE_STORE_API_BASE_URL = "https://fakestoreapi.com"
    const val PRODUCT_COLS_PER_ROW = 2
    const val POSTAL_CODE_LENGTH = 6
    const val BANNER_AUTO_SCROLL_DELAY_TIME = 3000L
    const val APP_VERSION = BuildConfig.VERSION_NAME
    const val MIN_PASS_LENGTH = 6
    const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
}