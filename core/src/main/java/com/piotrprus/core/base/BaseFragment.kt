package com.piotrprus.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.piotrprus.core.utils.event.Event
import com.piotrprus.core.utils.event.EventObserver

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    protected lateinit var binding: VDB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (this::class.annotations
            .find { it is LayoutResId } as? LayoutResId)!!
            .let {
                binding = DataBindingUtil.inflate(inflater, it.resId, container, false)
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        start()
    }

    protected abstract fun start()

    fun <T> LiveData<T>.observe(observe: ((value: T) -> Unit)) {
        this.observe(viewLifecycleOwner, Observer { value -> observe(value) })
    }

    fun <T> LiveData<Event<T>>.observeEvent(observe: ((value: T) -> Unit)) {
        this.observe(viewLifecycleOwner, EventObserver { value -> observe(value) })
    }
}
