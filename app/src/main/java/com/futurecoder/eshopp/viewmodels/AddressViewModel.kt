package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.services.RoomDatabaseService
import com.futurecoder.eshopp.utils.ioDispatcher
import com.futurecoder.eshopp.utils.isValidPostalCode
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddressViewModel"

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService,
    private val databaseService: RoomDatabaseService
) : EShoppViewModel() {
    init {
        getUserAddress()
    }

    // TODO: needs to be implemented in better way
    var addressIdToDelete: Long = -1

    private val addressMSF: MutableStateFlow<List<Address>> = MutableStateFlow(emptyList())
    val addressSF: StateFlow<List<Address>> = addressMSF.asStateFlow()

    private val addressState: MutableState<Address> = mutableStateOf(Address())
    val address: String
        get() = addressState.value.address

    private val city: String
        get() = addressState.value.city

    private val state: String
        get() = addressState.value.state

    private val country: String
        get() = addressState.value.country

    private val postalCode: String
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
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                databaseService.insertUserAddress(address)
            }.onSuccess {
                Log.d(TAG, "Address Successfully Added")
            }.onFailure {
                Log.e(TAG, "Address Add Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }

    private fun getUserAddress() {
        viewModelScope.launch(ioDispatcher) {
            kotlin.runCatching {
                databaseService.fetchUserAddresses()
            }.onSuccess {
                addressMSF.value = it
            }.onFailure {
                Log.e(TAG, "Fetching Address Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }

    fun editAddress(addressId: Long, actualAddress: String) {
        
    }

    fun deleteAddress(addressId: Long) {
        viewModelScope.launch(ioDispatcher) {
            kotlin.runCatching {

                databaseService.deleteAddress(addressSF.value.first {
                    Log.d(TAG, "addressId1: ${it.id} addressId2:- $addressId")
                    it.id == addressId
                })
            }.onSuccess {
                addressMSF.value = addressSF.value.filter {
                    it.id != addressId
                }
            }.onFailure {
                Log.e(TAG, "Address Deletion Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }
}