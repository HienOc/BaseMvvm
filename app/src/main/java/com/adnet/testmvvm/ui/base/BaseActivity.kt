package com.adnet.testmvvm.ui.base

import android.content.IntentFilter
import android.net.ConnectivityManager
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
import com.adnet.testmvvm.data.db.AppDatabase
import com.adnet.testmvvm.data.model.event.ConnectEvent
import com.adnet.testmvvm.utils.ConnectivityReceiver
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.android.inject

abstract class BaseActivity<
        ViewBinding : ViewDataBinding,
        ViewModel : BaseViewModel
        > : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel
    private val connectivityReceiver = ConnectivityReceiver()
    val appDatabase: AppDatabase by inject()

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
        initListener()
        observeViewModel()
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        EventBus.getDefault().register(this)
    }

    private fun initViewBinding() {
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.lifecycleOwner = this
    }

    abstract fun initView()

    abstract fun initListener()

    abstract fun observeViewModel()

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        ConnectivityReceiver.connectivityReceiverListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        unregisterReceiver(connectivityReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        EventBus.getDefault().post(ConnectEvent(isConnected))
    }

    protected fun addFragment(id: Int, fragment: Fragment, addToBackStack: Boolean) =
        supportFragmentManager.beginTransaction().add(id, fragment).apply {
            if (addToBackStack) addToBackStack(null)
        }.commit()

    @Subscribe
    fun onMessageEvent(event: ConnectEvent) {
    }
}
