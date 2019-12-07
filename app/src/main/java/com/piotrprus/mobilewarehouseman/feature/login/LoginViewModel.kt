package com.piotrprus.mobilewarehouseman.feature.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuthException
import com.piotrprus.domain.usecase.FacebookSignInUseCase
import com.piotrprus.model.AuthenticationState
import kotlinx.coroutines.launch

class LoginViewModel(private val facebookSignInUseCase: FacebookSignInUseCase) : ViewModel() {

    val authenticationState = MutableLiveData<AuthenticationState>()

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
}
