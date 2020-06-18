package com.adnet.testmvvm.ui.navigation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.adnet.testmvvm.R
import com.adnet.testmvvm.databinding.ActivityNavigationBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import com.adnet.testmvvm.utils.disableTooltipText
import com.adnet.testmvvm.utils.setupWithNavControllerr
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : BaseActivity<ActivityNavigationBinding, NavigationViewModel>() {

    override val viewModel: NavigationViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.activity_navigation

    private var currentNavController: LiveData<NavController>? = null

    override fun initView() {
        bottomNavigationView.disableTooltipText()
        setupBottomNavigationBar()
    }

    override fun initListener() {

    }

    override fun observeViewModel() {

    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navGraphIds =
            listOf(R.navigation.home, R.navigation.down, R.navigation.list, R.navigation.setting)

        val controller = bottomNavigationView.setupWithNavControllerr(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainerView,
            intent = intent
        )

        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.navigation)

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            Log.d("HienOcccc", navController.currentDestination?.label.toString())
            //  SharePreference.ORIGIN_NEXT = navController.currentDestination?.label.toString()
            // setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }
}