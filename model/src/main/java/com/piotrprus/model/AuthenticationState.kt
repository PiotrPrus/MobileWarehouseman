package com.piotrprus.model

sealed class AuthenticationState {
    object Authenticated : AuthenticationState()
    class Failed(val msg: String = "") : AuthenticationState()
}