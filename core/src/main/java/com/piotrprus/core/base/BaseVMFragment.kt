package com.piotrprus.core.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseVMFragment<VM : ViewModel, VDB : ViewDataBinding>(clazz: KClass<VM>) :
    BaseFragment<VDB>() {

    protected val viewModel: VM by viewModel(clazz)
}