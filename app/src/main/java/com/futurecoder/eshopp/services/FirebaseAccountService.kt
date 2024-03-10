package com.futurecoder.eshopp.services

interface FirebaseAccountService {
    val currentUserId: String
    val hasUser: Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
