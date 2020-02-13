package com.adnet.testmvvm.ui.navigation.fragmentNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnet.testmvvm.R
import org.greenrobot.eventbus.EventBus

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onDestroy() {
        EventBus.getDefault().post("Back")
        super.onDestroy()
    }

}
