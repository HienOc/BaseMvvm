package com.adnet.iyashi.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.*

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
    duration = 500
}

val translateXYAnimation = TranslateAnimation(
    0f,
    0f,
    100f,
    0f
).apply {
    duration = 300
}

val translateXYAnimationLeft = TranslateAnimation(
    50f,
    0f,
    100f,
    0f
).apply {
    duration = 300
}

val translateXYAnimationRight = TranslateAnimation(
    -50f,
    0f,
    100f,
    0f
).apply {
    duration = 300
}

val translateXYAnimationEnd = TranslateAnimation(
    0f,
    0f,
    0f,
    100f
).apply {
    duration = 200
}

val translateXYAnimationEndLeft = TranslateAnimation(
    0f,
    100f,
    0f,
    100f
).apply {
    duration = 200
}

val translateXYAnimationEndRight = TranslateAnimation(
    0f,
    -100f,
    0f,
    100f
).apply {
    duration = 200
}

val fade= AlphaAnimation(0f,1f).apply {
    duration=1500
}


val rotate = RotateAnimation(
    180f, 360f,
    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
    0.5f
).apply {
    duration = 300
}

fun doBounceAnimation(targetView: View) {
    val animator = ObjectAnimator.ofFloat(targetView, "translationY", 0f, -10f, 0f)
    animator.interpolator = BounceInterpolator()
    animator.duration = 1000
    animator.start()
}