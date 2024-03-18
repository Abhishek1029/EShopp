package com.futurecoder.eshopp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.utils.CLConstants
import com.futurecoder.eshopp.utils.CLConstants.FIRE_STORE_ADDRESSES_COLLECTION
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService
) : EShoppViewModel() {

    val addressState: MutableState<Address> = mutableStateOf(Address())
    val address: String
        get() = addressState.value.address

    val city: String
        get() = addressState.value.city

    val state: String
        get() = addressState.value.state

    val country: String
        get() = addressState.value.country

    val postalCode: String
        get() = addressState.value.postalCode

    fun isCurrentUser(): Boolean {
        return firebaseAccountService.hasUser
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAccountService.currentUser
    }

    fun logoutUser() {
        viewModelScope.launch {
            firebaseAccountService.signOut()
        }
    }

    fun addAddress() {
        if (address.isBlank()) {
            return
        }
        if (city.isBlank()) {
            return
        }
        if (state.isBlank()) {
            return
        }
        if (country.isBlank()) {
            return
        }
        if (postalCode.isBlank()) {
            return
        }
        val address = Address(address, city, state, country, postalCode)
    }
    /*fun getCurrentUser(): Boolean {
        return firebaseAccountService.currentUserId
    }*/
}