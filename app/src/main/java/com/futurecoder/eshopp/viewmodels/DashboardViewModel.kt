package com.futurecoder.eshopp.viewmodels

import androidx.lifecycle.ViewModel
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService
) : ViewModel() {

    fun isCurrentUser(): Boolean {
        return firebaseAccountService.hasUser
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAccountService.currentUser
    }
}