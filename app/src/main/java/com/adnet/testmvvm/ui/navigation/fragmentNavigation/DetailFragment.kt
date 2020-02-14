package com.adnet.testmvvm.ui.navigation.fragmentNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.adnet.testmvvm.R
import com.adnet.testmvvm.utils.rotate
import com.adnet.testmvvm.utils.translateXAnimation
import com.adnet.testmvvm.utils.translateYAnimation
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.*
import org.greenrobot.eventbus.EventBus


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageViewBack.startAnimation(rotate)
        imageViewDetail.startAnimation(translateXAnimation)
     //   imageViewBack.startAnimation(translateYAnimation)

    }

    override fun onDestroy() {
        EventBus.getDefault().post("Back")
        super.onDestroy()
    }

}
