package com.adnet.testmvvm.utils

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation

val translateYAnimation = TranslateAnimation(
    -50f,
    0f,
    0f,
    0f
).apply {
    duration = 300
}

val translateXAnimation = TranslateAnimation(
    0f,
    0f,
    100f,
    0f
).apply {
    duration = 1000
}


val rotate = RotateAnimation(
    180f, 360f,
    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
    0.5f
).apply {
    duration = 300
}