package com.piotrprus.mobilewarehouseman.feature.main

import com.piotrprus.mobilewarehouseman.R
import com.piotrprus.mobilewarehouseman.databinding.ActivityMainBinding
import com.piotrprus.core.base.BaseVMActivity
import com.piotrprus.core.base.LayoutResId

@LayoutResId(R.layout.activity_main)
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class) {

    override fun start() {
        super.start()
        binding.viewModel = viewModel
    }
}
