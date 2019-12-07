package com.piotrprus.domain.usecase

import com.facebook.AccessToken
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FacebookSignInUseCase(private val auth: FirebaseAuth) {
    suspend fun execute(token: AccessToken): FirebaseUser? {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential).await()
        return auth.currentUser ?: throw FirebaseAuthException("", "")
    }
}