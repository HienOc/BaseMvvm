package com.adnet.testmvvm.ui.navigation.fragmentNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.adnet.testmvvm.R
import com.adnet.testmvvm.utils.rotate
import kotlinx.android.synthetic.main.home_fragment.*
import org.greenrobot.eventbus.EventBus

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonNext.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.fragmentDetail)
            EventBus.getDefault().post("Next")
        }
        buttonNext.startAnimation(rotate)
    }
}
