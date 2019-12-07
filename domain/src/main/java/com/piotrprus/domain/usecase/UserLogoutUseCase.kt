package com.piotrprus.domain.usecase

import com.google.firebase.auth.FirebaseAuth

class UserLogoutUseCase(private val auth: FirebaseAuth) {
    fun execute() {
        auth.signOut()
    }
}