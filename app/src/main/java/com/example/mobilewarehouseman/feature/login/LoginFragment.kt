package com.example.mobilewarehouseman.feature.login

import com.example.mobilewarehouseman.R
import com.example.mobilewarehouseman.databinding.LoginFragmentBinding
import com.piotrprus.core.base.BaseVMFragment
import com.piotrprus.core.base.LayoutResId

@LayoutResId(R.layout.login_fragment)
class LoginFragment : BaseVMFragment<LoginViewModel, LoginFragmentBinding>(LoginViewModel::class) {
    override fun start() {
        binding.viewModel = viewModel
    }

}
