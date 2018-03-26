package com.example.flukepc.coworkadmin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CoWorkDetail(@SerializedName("id") val id: String? = null,
                        @SerializedName("name") val name: String? = null,
                        @SerializedName("address") val address: String? = null,
                        @SerializedName("status") val status: Boolean? = null,
                        @SerializedName("gallery") val gallery: ImageGallery? = null,
                        @SerializedName("approve") val approve: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readParcelable(ImageGallery::class.java.classLoader),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeValue(status)
        parcel.writeParcelable(gallery, flags)
        parcel.writeString(approve)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoWorkDetail> {
        override fun createFromParcel(parcel: Parcel): CoWorkDetail {
            return CoWorkDetail(parcel)
        }

        override fun newArray(size: Int): Array<CoWorkDetail?> {
            return arrayOfNulls(size)
        }
    }
}

data class ListCoWork(@SerializedName("data") var results: List<CoWorkDetail>? = null)