@file:Suppress("DEPRECATION")

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding2.view.RxView
import org.greenrobot.eventbus.EventBus
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

@SuppressLint("CheckResult")
fun View.setSafeOnClickListener(onClick: (View) -> Unit) {
    RxView.clicks(this).throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe {
        onClick(this)
    }
}

@SuppressLint("CheckResult")
fun View.setSafeOnClickListenerAdd(onClick: (View) -> Unit) {
    RxView.clicks(this).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe {
        onClick(this)
    }
}

@SuppressLint("CheckResult")
fun View.setSafeOnClickListenerDown(onClick: (View) -> Unit) {
    RxView.clicks(this).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
        onClick(this)
    }
}

fun localizationString(key: Int, context: Context): String {
    val resources = context.resources
    return resources.getString(key)
}

fun View.hideKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}


fun setUi(views1: View, views2: View) {
    views1.show()
    views2.gone()
}

fun ImageView.setImageUrl(url: String?) =
    Glide.with(context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)


fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo: NetworkInfo? = null
    activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}

fun Int.formatDecimal(): String = this.let {
    DecimalFormat("#00").format(it)
}

fun secToMi(s: Int): String {
    val time = s / 1000
    val sec = time % 60
    val min = time / 60
    return String.format("%02d:%02d", min, sec)
}

fun logoutStatus(s: Int) {
    if (s == 400 ||
        s == 401 ||
        s == 40
    ) EventBus.getDefault().post("LOGOUT")
}

fun Resources.getColoredBitmap(resourceId: Int, newColor: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inMutable = true
    val bmp = BitmapFactory.decodeResource(this, resourceId, options)
    val paint = Paint()
    val filter = PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN)
    paint.colorFilter = filter
    val canvas = Canvas(bmp)
    canvas.drawBitmap(bmp, 0f, 0f, paint)
    return bmp
}


fun openWebPage(context: Context, url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun isOnline(context: Context): Boolean {
    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
    return networkInfo?.isConnected == true
}

fun isWifi(context: Context): Boolean {
    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
    return networkInfo?.type == ConnectivityManager.TYPE_WIFI
}
