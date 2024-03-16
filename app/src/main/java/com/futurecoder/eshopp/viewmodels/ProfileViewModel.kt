package com.futurecoder.eshopp.viewmodels

import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService
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
    /*fun getCurrentUser(): Boolean {
        return firebaseAccountService.currentUserId
    }*/
}