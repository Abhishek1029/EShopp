package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "EShoppViewModel"

@HiltViewModel
open class EShoppViewModel @Inject constructor() : ViewModel() {

    fun launchCatching(calledFunction: suspend () -> Task<AuthResult>) {
        viewModelScope.launch {
            kotlin.runCatching {
                Log.d(TAG, "kotlin.runCatching called")
                calledFunction()
            }.onSuccess {
                it.addOnSuccessListener {
                    Log.d(TAG, "On Success Listener")
                }
                it.addOnFailureListener { failure ->
                    Log.d(
                        TAG,
                        "Account Creation Failed with Exception:- ${failure.localizedMessage}"
                    )
                }
            }.onFailure {
                Log.e(TAG, "Account Creation Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }
}