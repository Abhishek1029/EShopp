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

private const val TAG = "ProfileViewModel"

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService,
    private val databaseService: RoomDatabaseService
) : EShoppViewModel() {


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

}