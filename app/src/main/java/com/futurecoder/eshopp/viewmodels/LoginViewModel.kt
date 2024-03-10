package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.futurecoder.eshopp.data.LoginState
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.utils.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService
) : ViewModel() {

    private val loginState = mutableStateOf(LoginState())
    private val email: String
        get() = loginState.value.email

    private val password: String
        get() = loginState.value.password

    fun onLoginClick() {
        if (!email.isValidEmail()) {
            Log.e(TAG, "Invalid Email Address")
            return
        }
        if (password.isBlank()) {
            Log.e(TAG, "Password should not be empty")
            return
        }
    }
}