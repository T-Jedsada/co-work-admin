package com.example.flukepc.coworkadmin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CoWorkDetail(val _id: String? = null,
                        val name: String? = null,
                        val latitude: Double? = null,
                        val longitude: Double? = null,
                        val details: String? = null,
                        val price_per_hour: Int? = null,
                        val rarting: String,
                        val address: String? = null,
                        val provider_id: String? = null,
                        val status: Boolean? = null,
                        val gellery: ImageGallery? = null,
                        val approve : String?=null):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readParcelable(ImageGallery::class.java.classLoader),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(name)
        parcel.writeValue(latitude)
        parcel.writeValue(longitude)
        parcel.writeString(details)
        parcel.writeValue(price_per_hour)
        parcel.writeString(rarting)
        parcel.writeString(address)
        parcel.writeString(provider_id)
        parcel.writeValue(status)
        parcel.writeParcelable(gellery, flags)
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

