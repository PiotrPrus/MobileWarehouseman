package com.example.mobilewarehouseman.di

import com.example.mobilewarehouseman.feature.login.LoginViewModel
import com.example.mobilewarehouseman.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
}