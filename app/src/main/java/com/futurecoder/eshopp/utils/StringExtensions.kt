package com.futurecoder.eshopp.utils

import android.util.Patterns
import com.futurecoder.eshopp.utils.CLConstants.MIN_PASS_LENGTH
import com.futurecoder.eshopp.utils.CLConstants.PASS_PATTERN
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PASS_LENGTH && Pattern.compile(PASS_PATTERN)
        .matcher(this).matches()
}