package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.SignupState
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.utils.CLConstants.FIRE_STORE_USERS_COLLECTION
import com.futurecoder.eshopp.utils.isValidEmail
import com.futurecoder.eshopp.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SignupViewModel"

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService
) : EShoppViewModel() {

    private val signupState = mutableStateOf(SignupState())
    val name: String
        get() = signupState.value.name
    val email: String
        get() = signupState.value.email
    val password: String
        get() = signupState.value.password

    fun onEmailChange(emailAddress: String) {
        signupState.value.email = emailAddress
    }

    fun onPasswordChange(password: String) {
        signupState.value.password = password
    }

    fun onSignUpClick() {
        Log.d(TAG, "onSignUpClick called")
        if (!email.isValidEmail()) {
            Log.d(TAG, "Not a valid email")
            return
        }
        if (!password.isValidPassword()) {
            Log.d(TAG, "Not a valid Password")
            return
        }
        viewModelScope.launch {
            kotlin.runCatching {
                Log.d(TAG, "kotlin.runCatching called")
                firebaseAccountService.createAccount(email, password)
            }.onSuccess {
                it.addOnSuccessListener { userData ->
                    Log.d(TAG, "On Success Listener ${userData.user?.email}")
                }
                it.addOnFailureListener { failure ->
                    Log.d(
                        TAG,
                        "Account Creation Failed with Exception:- ${failure.localizedMessage}"
                    )
                }
            }.onFailure {
                Log.e(TAG, "Account Creation Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }
}