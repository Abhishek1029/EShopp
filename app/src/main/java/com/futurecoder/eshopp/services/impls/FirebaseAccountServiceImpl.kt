package com.futurecoder.eshopp.services.impls

import com.futurecoder.eshopp.services.FirebaseAccountService
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null

    override fun signIn() {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }
}