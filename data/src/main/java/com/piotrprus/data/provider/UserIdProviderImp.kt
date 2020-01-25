package com.piotrprus.data.provider

import com.google.firebase.auth.FirebaseAuth

class UserIdProviderImp(private val auth: FirebaseAuth): UserIdProvider {
    override val userId: String
        get() = auth.uid ?: throw Exception()
}