package com.piotrprus.mobilewarehouseman.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.piotrprus.core.base.BaseVMFragment
import com.piotrprus.core.base.LayoutResId
import com.piotrprus.mobilewarehouseman.R
import com.piotrprus.mobilewarehouseman.databinding.LoginFragmentBinding
import timber.log.Timber

@LayoutResId(R.layout.login_fragment)
class LoginFragment : BaseVMFragment<LoginViewModel, LoginFragmentBinding>(
    LoginViewModel::class
) {
    private val facebookCallback: CallbackManager by lazy { CallbackManager.Factory.create() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun start() {
        Timber.d("start main method")
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
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookCallback.onActivityResult(requestCode, resultCode, data)
    }

}
