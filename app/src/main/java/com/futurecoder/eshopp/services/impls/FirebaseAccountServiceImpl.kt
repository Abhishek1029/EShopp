package com.futurecoder.eshopp.services.impls

import com.futurecoder.eshopp.services.FirebaseAccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}