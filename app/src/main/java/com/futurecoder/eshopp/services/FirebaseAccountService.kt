package com.futurecoder.eshopp.services

interface FirebaseAccountService {
    val currentUserId: String
    val hasUser: Boolean
    fun signIn()
    fun signOut()
}
