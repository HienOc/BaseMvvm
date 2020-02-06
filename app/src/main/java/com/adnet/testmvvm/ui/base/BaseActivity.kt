package com.adnet.testmvvm.ui.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.adnet.testmvvm.BR
import com.adnet.testmvvm.ui.listsvideo.EventTest
import kotlinx.android.synthetic.main.activity_video.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseActivity<
        ViewBinding : ViewDataBinding,
        ViewModel : BaseViewModel
        > : AppCompatActivity() {

    lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
        initListener()
        observeViewModel()
        videoLoading()
        EventBus.getDefault().register(this)
    }

    private fun initViewBinding() {
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.lifecycleOwner = this
    }

    abstract fun initView()

    abstract fun initListener()

    open fun observeViewModel() {
        viewModel.messenger.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    protected open fun videoLoading() {
    }

    protected fun addFragment(id: Int, fragment: Fragment, addToBackStack: Boolean) =
        supportFragmentManager.beginTransaction().add(id, fragment).apply {
            if (addToBackStack) addToBackStack(null)
        }.commit()

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
     open fun eventBus(event: EventTest) {
        Log.d("Leuuuuu","JJJJJJJJJJJ")
    }
}
