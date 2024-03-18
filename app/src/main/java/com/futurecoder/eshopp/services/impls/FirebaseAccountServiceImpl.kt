package com.futurecoder.eshopp.services.impls

import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.data.SignupState
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import javax.inject.Inject

class FirebaseAccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : FirebaseAccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val currentUser: FirebaseUser?
        get() = auth.currentUser
    override val hasUser: Boolean
        get() = auth.currentUser != null

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun createAccount(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun insertUserInFireStore(
        collectionName: String,
        signupData: SignupState
    ): Task<Void> {
        return fireStore.collection(collectionName).document(signupData.email).set(
            signupData
        )
    }

    override suspend fun addAddressInFireStore(
        collectionName: String,
        userEmail: String,
        address: Address
    ): Task<Void> {
        return fireStore.collection(collectionName).document(userEmail).update("addresses", FieldValue.arrayUnion(address))
    }

    override suspend fun getAddress(collectionName: String,userEmail: String, address: Address): DocumentReference {
        return fireStore.collection(collectionName).document(userEmail)
    }

    override suspend fun queryEmail(
        collectionName: String,
        email: String
    ): Query {
        return fireStore.collection(collectionName).whereEqualTo("email", email)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}