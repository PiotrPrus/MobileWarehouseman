package com.piotrprus.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.piotrprus.core.utils.event.Event
import com.piotrprus.core.utils.event.EventObserver

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VDB
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this::class.annotations
            .find { it is LayoutResId } as? LayoutResId)!!
            .let {
                binding = DataBindingUtil.setContentView(this, it.resId)
                binding.setLifecycleOwner(this)
//                    binding.setLifecycleOwner { this.lifecycle }
            }
        start()
    }

    open fun start() {}

    fun <T> LiveData<T>.observe(observe: ((value: T?) -> Unit)) {
        this.observe(this@BaseActivity, Observer { value -> observe(value) })
    }

    fun <T> LiveData<Event<T>>.observeEvent(observe: ((value: T?) -> Unit)) {
        this.observe(this@BaseActivity, EventObserver { value -> observe(value) })
    }
}