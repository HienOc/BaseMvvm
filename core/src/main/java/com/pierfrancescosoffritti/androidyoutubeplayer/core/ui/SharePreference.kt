package com.pierfrancescosoffritti.androidyoutubeplayer.core.ui

import com.chibatching.kotpref.KotprefModel

object SharePreference : KotprefModel() {
    override val kotprefName = "language_settings"
    var EDIT by stringPref("", "edit")
    var CHANGE by stringPref("", "change")
    var CHECK_PICTURE by booleanPref(false, "check_picture")
    var MUSIC by stringPref("", "music")
}