package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import com.chibatching.kotpref.KotprefModel

object SharePreference : KotprefModel() {
    override val kotprefName = "language_settings"
    var EDIT by stringPref("", "edit")
    var CHANGE by stringPref(" ", "change")
    var CHECK_PICTURE by booleanPref(false, "check_picture")
    var CHECK_ITEM by booleanPref(false, "check_item")
    var CHECK_STATUS by booleanPref(false, "check_status")
    var MUSIC by stringPref("", "music")

}