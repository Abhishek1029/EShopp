package com.futurecoder.eshopp.utils

import android.util.Patterns
import com.futurecoder.eshopp.utils.EShoppConstants.MIN_PASS_LENGTH
import com.futurecoder.eshopp.utils.EShoppConstants.PASS_PATTERN
import com.futurecoder.eshopp.utils.EShoppConstants.POSTAL_CODE_LENGTH
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PASS_LENGTH && Pattern.compile(PASS_PATTERN)
        .matcher(this).matches()
}

fun String.isValidPostalCode(): Boolean {
    return this.isNotBlank() && this.length == POSTAL_CODE_LENGTH
}