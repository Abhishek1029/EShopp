package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.utils.CLConstants
import com.futurecoder.eshopp.utils.CLConstants.FIRE_STORE_ADDRESSES_COLLECTION
import com.futurecoder.eshopp.utils.isValidPostalCode
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ProfileViewModel"

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

    fun onAddressChange(address: String) {
        addressState.value.address = address
    }

    fun onCityChange(city: String) {
        addressState.value.city = city
    }

    fun onStateChange(state: String) {
        addressState.value.state = state
    }

    fun onCountryChange(country: String) {
        addressState.value.country = country
    }

    fun onPostalCodeChange(postalCode: String) {
        addressState.value.postalCode = postalCode
    }

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
        Log.d(TAG, "Add Address called")
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
        if (!postalCode.isValidPostalCode()) {
            return
        }
        Log.d(TAG, "Add Address called Again")
        val address = Address(address, city, state, country, postalCode)
        viewModelScope.launch {
            kotlin.runCatching {

            }.onSuccess {
            }.onFailure {

            }
        }
    }
    /*fun getCurrentUser(): Boolean {
        return firebaseAccountService.currentUserId
    }*/
}