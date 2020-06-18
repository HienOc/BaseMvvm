package com.adnet.testmvvm.data.model.entities

class BannerModel (
    var bannerId: Int,
    var bannerImage: String
){
    companion object {
        const val TABLE_NAME = "banner_table"
        const val BANNER_ID = "image_id"
        const val BANNER_IMAGE = "image_url"
    }
}
