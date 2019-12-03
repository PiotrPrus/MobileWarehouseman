package com.piotrprus.mobilewarehouseman.feature.login

import android.content.Intent
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

    override fun start() {
        binding.viewModel = viewModel
        binding.facebookLoginButton.fragment = this
        binding.facebookLoginButton.registerCallback(
            facebookCallback,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Timber.d("Login success")
                }

                override fun onCancel() {
                    Timber.d("Login cancel")
                }

                override fun onError(error: FacebookException?) {
                    Timber.d(error, "Login success")
                }

            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookCallback.onActivityResult(requestCode, resultCode, data)
    }

}
