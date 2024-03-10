package com.futurecoder.eshopp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.futurecoder.eshopp.data.LoginState
import com.futurecoder.eshopp.utils.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val loginState = mutableStateOf(LoginState())
    private val email: String
        get() = loginState.value.email

    private val password: String
        get() = loginState.value.password

    fun onLoginClick() {
        if (!email.isValidEmail()) {
            return
        }
        if (password.isBlank()) {
            return
        }
    }
}