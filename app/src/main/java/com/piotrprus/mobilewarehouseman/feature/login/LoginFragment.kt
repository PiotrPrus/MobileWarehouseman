package com.piotrprus.mobilewarehouseman.feature.login

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
    override fun start() {
        binding.viewModel = viewModel
        val facebookCallback = CallbackManager.Factory.create()
        binding.facebookLoginButton.apply {
            fragment = this@LoginFragment
            registerCallback(facebookCallback, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Timber.d("AAA, Login success")
                }

                override fun onCancel() {
                    Timber.d("AAA, Login cancel")
                }

                override fun onError(error: FacebookException?) {
                    Timber.d(error, "AAA, Login success")
                }

            })
        }

    }

}
