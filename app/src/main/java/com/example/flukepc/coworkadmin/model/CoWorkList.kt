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
                        val gellery: ImageGallery? = null) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readParcelable<ImageGallery>(ImageGallery::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(_id)
        writeString(name)
        writeValue(latitude)
        writeValue(longitude)
        writeString(details)
        writeValue(price_per_hour)
        writeString(rarting)
        writeString(address)
        writeString(provider_id)
        writeValue(status)
        writeParcelable(gellery, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CoWorkDetail> = object : Parcelable.Creator<CoWorkDetail> {
            override fun createFromParcel(source: Parcel): CoWorkDetail = CoWorkDetail(source)
            override fun newArray(size: Int): Array<CoWorkDetail?> = arrayOfNulls(size)
        }
    }
}

data class ListCoWork(@SerializedName("data") var results: List<CoWorkDetail>? = null)

