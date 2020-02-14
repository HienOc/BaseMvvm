package com.adnet.testmvvm.ui.navigation

import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.adnet.testmvvm.R
import com.adnet.testmvvm.databinding.ActivityNavigationBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import org.greenrobot.eventbus.Subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : BaseActivity<ActivityNavigationBinding,NavigationViewModel>(),
    BottomNavigationView.OnNavigationItemReselectedListener {

    override val viewModel: NavigationViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_navigation

    override fun initView() {
        bottomNavigationView.getOrCreateBadge(R.id.menuImage).number=2
        Navigation.findNavController(this, R.id.fragmentContainerView)?.let { navigation ->
            NavigationUI.setupWithNavController(bottomNavigationView, navigation)
        }

       // bottomNavigationView.getOrCreateBadge(R.id.menuImage).number=2
    }

    override fun initListener() {
        bottomNavigationView.setOnNavigationItemReselectedListener(this)
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

    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId){
            R.id.menuImage->{
                bottomNavigationView.getBadge(item.itemId)?.let {
                    if(it.isVisible) bottomNavigationView.removeBadge(item.itemId)
                }
            }
        }
    }
}
