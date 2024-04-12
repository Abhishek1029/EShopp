package com.futurecoder.eshopp

import com.futurecoder.eshopp.utils.isValidPassword
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestValidPassword {
    companion object{
        const val PASSWORD_WITH_LETTER_AND_NUMBERS = "aa1234"
        const val PASSWORD_LESS_THAN_SIX_DIGIT = "aa123"
        const val PASSWORD_WITH_SIX_DIGIT_WITHOUT_CAPITAL_LETTER = "aa1234"
        const val PASSWORD_MORE_THAN_SIX_DIGIT_WITHOUT_CAPITAL_LETTER = "aa12345"
        const val PASSWORD_SIX_DIGIT_WITH_CAPITAL_LETTER_CONTAIN_NUMBERS = "Aa1234"
        const val PASSWORD__MORE_THAN_SIX_DIGIT_WITH_CAPITAL_LETTER_CONTAIN_NUMBERS = "Aa1234"
    }

    @Before
    fun printStartingLog(){
        println("Starting Validation of Passwords")
    }

    @Test
    fun `password with letter and number returns error`() {
        val result = PASSWORD_WITH_LETTER_AND_NUMBERS.isValidPassword()
        assertEquals(result, false)
    }

    @Test
    fun `password less than six digit returns error`() {
        val result = PASSWORD_LESS_THAN_SIX_DIGIT.isValidPassword()
        assertEquals(result, false)
    }

    @Test
    fun `password with six digit without capital letter returns error`() {
        val result = PASSWORD_WITH_SIX_DIGIT_WITHOUT_CAPITAL_LETTER.isValidPassword()
        assertEquals(result, false)
    }
    @Test
    fun `password more than six digit without capital letter returns error`() {
        val result = PASSWORD_MORE_THAN_SIX_DIGIT_WITHOUT_CAPITAL_LETTER.isValidPassword()
        assertEquals(result, false)
    }

    @Test
    fun `password with six digit and with one capital letter and contain numbers returns successfull`() {
        val result = PASSWORD_SIX_DIGIT_WITH_CAPITAL_LETTER_CONTAIN_NUMBERS.isValidPassword()
        assertEquals(result, true)
    }

    @Test
    fun `password more than six digit and with one capital letter and contain numbers returns successfull`() {
        val result = PASSWORD__MORE_THAN_SIX_DIGIT_WITH_CAPITAL_LETTER_CONTAIN_NUMBERS.isValidPassword()
        assertEquals(result, true)
    }

    @After
    fun printEndingLog(){
        println("Validation of Passwords Done")
    }
}