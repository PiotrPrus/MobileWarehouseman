package com.example.mobilewarehouseman.feature.main

import com.example.mobilewarehouseman.R
import com.example.mobilewarehouseman.databinding.ActivityMainBinding
import com.piotrprus.core.base.BaseVMActivity
import com.piotrprus.core.base.LayoutResId

@LayoutResId(R.layout.activity_main)
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class) {

    override fun onStart() {
        super.onStart()
        binding.viewModel = viewModel
    }
}
