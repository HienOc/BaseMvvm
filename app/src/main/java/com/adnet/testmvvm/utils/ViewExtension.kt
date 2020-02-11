@file:Suppress("DEPRECATION")

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View.OnClickListener.assignViews(vararg views: View?) {
    views.forEach { it?.setOnClickListener(this) }
}

fun View.hideKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

@SuppressLint("CheckResult")
fun View.setSafeOnClickListener(onClick: (View) -> Unit) {
    RxView.clicks(this).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe {
        onClick(this)
    }
}


fun ImageView.setImageUrl(url: String) = Glide.with(context)
    .load(url)
    .into(this)

fun setUi(views1: View,views2: View){
    views1.show()
    views2.gone()
}

fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo: NetworkInfo? = null
    activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}

fun Int.formatDecimal(): String = this.let {
    DecimalFormat("#00").format(it)
}

