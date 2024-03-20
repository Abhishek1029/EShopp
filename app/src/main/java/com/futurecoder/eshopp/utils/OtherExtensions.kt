package com.futurecoder.eshopp.utils

import com.futurecoder.eshopp.data.Address

fun Address.generateActualAddress(): String {
    return address.plus(", ").plus(city).plus(", ").plus(state)
        .plus(", ").plus(country).plus(", ")
        .plus(postalCode)
}