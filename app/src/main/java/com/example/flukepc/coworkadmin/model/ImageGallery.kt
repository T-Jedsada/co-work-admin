package com.example.flukepc.coworkadmin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ImageGallery(@SerializedName("image_01") val poster: String? = null) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(poster)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ImageGallery> = object : Parcelable.Creator<ImageGallery> {
            override fun createFromParcel(source: Parcel): ImageGallery = ImageGallery(source)
            override fun newArray(size: Int): Array<ImageGallery?> = arrayOfNulls(size)
        }
    }
}
