package com.adnet.testmvvm.data.model

import android.os.Parcel
import android.os.Parcelable

class VideoYoutube (
    var idVideo: String?,
    var name: String?,
    var urlImage: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idVideo)
        parcel.writeString(name)
        parcel.writeString(urlImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoYoutube> {
        override fun createFromParcel(parcel: Parcel): VideoYoutube {
            return VideoYoutube(parcel)
        }

        override fun newArray(size: Int): Array<VideoYoutube?> {
            return arrayOfNulls(size)
        }
    }

}
