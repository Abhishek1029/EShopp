package com.futurecoder.eshopp

import com.futurecoder.eshopp.utils.isValidEmail
import org.junit.Assert.assertTrue
import org.junit.Test

class TestValidEmail {
    companion object{
        const val VALID_EMAIL = "amar@email.com"
    }
    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(VALID_EMAIL.isValidEmail())
    }
}