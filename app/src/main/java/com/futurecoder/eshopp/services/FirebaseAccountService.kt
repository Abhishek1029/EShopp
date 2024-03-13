package com.futurecoder.eshopp.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseAccountService {
    val currentUserId: String
    val hasUser: Boolean
    suspend fun signIn(email: String, password: String): Task<AuthResult>

    suspend fun createAccount(email: String, password: String): Task<AuthResult>
    suspend fun signOut()
}
