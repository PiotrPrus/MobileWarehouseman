package com.piotrprus.mobilewarehouseman.feature.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthException
import com.piotrprus.core.utils.event.EventEmitter
import com.piotrprus.core.utils.event.emit
import com.piotrprus.domain.usecase.FacebookSignInUseCase
import com.piotrprus.domain.usecase.GoogleSignInUseCase
import com.piotrprus.model.AuthenticationState
import kotlinx.coroutines.launch

class LoginViewModel(private val facebookSignInUseCase: FacebookSignInUseCase,
                     private val googleSignInUseCase: GoogleSignInUseCase)
    : ViewModel() {

    val authenticationState = MutableLiveData<AuthenticationState>()
    val googleLoginClickEvent = EventEmitter()

    fun authenticateFacebookLogin(result: LoginResult) {
        viewModelScope.launch {
            try {
                facebookSignInUseCase.execute(result.accessToken)?.let {
                    authenticationState.postValue(AuthenticationState.Authenticated)
                } ?: run {
                    authenticationState.postValue(AuthenticationState.Failed())
                }
            } catch (e: FirebaseAuthException) {
                authenticationState.postValue(AuthenticationState.Failed())
            }
        }
    }

    fun authenticateGoogleLogin(account: GoogleSignInAccount) {
        viewModelScope.launch {
            try {
                googleSignInUseCase.execute(account)?.let {
                    authenticationState.postValue(AuthenticationState.Authenticated)
                } ?: kotlin.run {
                    authenticationState.postValue(AuthenticationState.Failed())
                }
            } catch (e: FirebaseAuthException) {
                authenticationState.postValue(AuthenticationState.Failed())
            }
        }
    }

    fun onGoogleButtonClicked() {
        googleLoginClickEvent.emit()
    }
}
