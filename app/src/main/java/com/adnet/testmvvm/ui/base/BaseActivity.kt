package com.adnet.testmvvm.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.adnet.testmvvm.BR

abstract class BaseActivity<
        ViewBinding : ViewDataBinding,
        ViewModel : BaseViewModel
        > : AppCompatActivity() {

    private lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(checkInternet()){
            initViewBinding()
            setupPermissions()
            initView()
            initListener()
            observeViewModel()
        }

    }

    private fun initViewBinding() {
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.lifecycleOwner = this
    }

    abstract fun initView()

    abstract fun checkInternet():Boolean

    abstract fun setupPermissions()

    abstract fun initListener()

    open fun observeViewModel() {
        viewModel.messenger.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    protected fun addFragment(id: Int, fragment: Fragment, addToBackStack: Boolean) =
        supportFragmentManager.beginTransaction().add(id, fragment).apply {
            if (addToBackStack) addToBackStack(null)
        }.commit()
}
