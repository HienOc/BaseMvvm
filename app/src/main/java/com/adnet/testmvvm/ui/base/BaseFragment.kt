package com.adnet.testmvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment<
        ViewBinding : ViewDataBinding,
        ViewModel : BaseViewModel
        > : Fragment(), LifecycleOwner {
    @get:LayoutRes
    abstract val layoutId: Int
    abstract val bindingVariable: Int

    private lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            setVariable(bindingVariable, viewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        initView()
        initData()
        observeViewModel()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun observeViewModel()

    protected fun addFragment(id: Int, fragment: Fragment, addToBackStack: Boolean) =
        activity?.supportFragmentManager?.beginTransaction()?.add(id, fragment)?.apply {
            if (addToBackStack) addToBackStack(null)
        }?.commit()
}
