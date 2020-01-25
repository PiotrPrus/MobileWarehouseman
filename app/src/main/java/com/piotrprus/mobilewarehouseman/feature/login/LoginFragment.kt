package com.piotrprus.mobilewarehouseman.feature.login

import android.content.Intent
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.piotrprus.core.base.BaseVMFragment
import com.piotrprus.core.base.LayoutResId
import com.piotrprus.mobilewarehouseman.R
import com.piotrprus.mobilewarehouseman.databinding.LoginFragmentBinding
import timber.log.Timber
import android.R.attr.data
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import sun.jvm.hotspot.utilities.IntArray
import android.R
import com.google.android.gms.common.api.ApiException


@LayoutResId(R.layout.login_fragment)
class LoginFragment : BaseVMFragment<LoginViewModel, LoginFragmentBinding>(
    LoginViewModel::class
) {
    private val facebookCallback: CallbackManager by lazy { CallbackManager.Factory.create() }
    private val googleSignInOptions: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }
    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(
            requireActivity(),
            googleSignInOptions
        )
    }

    override fun start() {
        binding.viewModel = viewModel
        binding.facebookLoginButton.fragment = this
        binding.facebookLoginButton.registerCallback(
            facebookCallback,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    loginResult?.let { result ->
                        viewModel.authenticateFacebookLogin(result)
                    }
                }

                override fun onCancel() {
                    Timber.d("Login cancel")
                }

                override fun onError(error: FacebookException?) {
                    Timber.d(error, "Login error")
                }

            })
        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.apply {
            authenticationState.observe {
                Toast.makeText(
                    requireContext(),
                    "$it",
                    Toast.LENGTH_SHORT
                ).show()
            }
            googleLoginClickEvent.observeEvent { googleSignIn() }
        }
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { viewModel.authenticateGoogleLogin(account) }
            } catch (e: Exception) {
                Timber.d("Login with google failed")
            }
        }
        facebookCallback.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val RC_SIGN_IN = 101
    }

}
