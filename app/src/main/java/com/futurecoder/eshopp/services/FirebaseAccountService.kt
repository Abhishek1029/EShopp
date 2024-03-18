package com.futurecoder.eshopp.services

import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.data.SignupState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query

interface FirebaseAccountService {
    val currentUserId: String
    val currentUser: FirebaseUser?
    val hasUser: Boolean
    suspend fun signIn(email: String, password: String): Task<AuthResult>

    suspend fun createAccount(email: String, password: String): Task<AuthResult>

    suspend fun insertUserInFireStore(collectionName: String, signupData: SignupState): Task<Void>

    suspend fun addAddressInFireStore(
        collectionName: String,
        userEmail: String,
        address: Address
    ): Task<Void>

    suspend fun getAddress(collectionName: String, address: Address): Query

    suspend fun queryEmail(collectionName: String, email: String): Query
    suspend fun signOut()
}
