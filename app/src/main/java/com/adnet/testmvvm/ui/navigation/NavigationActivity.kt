package com.adnet.testmvvm.ui.navigation

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.adnet.testmvvm.R
import com.adnet.testmvvm.databinding.ActivityNavigationBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import org.greenrobot.eventbus.Subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : BaseActivity<ActivityNavigationBinding,NavigationViewModel>() {

    override val viewModel: NavigationViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_navigation

    override fun initView() {
        Navigation.findNavController(this, R.id.fragmentContainerView)?.let { navigation ->
            NavigationUI.setupWithNavController(bottomNavigationView, navigation)
        }
    }

    override fun initListener() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Subscribe
    fun eventBusNavigation(event: String) {
        if(event=="Next"){
            bottomNavigationView.visibility= View.GONE
        }
        if(event=="Back"){
            bottomNavigationView.visibility= View.VISIBLE
        }
    }
}
