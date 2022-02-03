package com.clonecoding.clone_airbnb.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class HouseDtoItem(

    @SerializedName("id")
    val id: String,

    @SerializedName("imgUrl")
    val imgUrl: String,

    @SerializedName("lat")
    val _lat: String,

    @SerializedName("lng")
    val _lng: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("title")
    val title: String
) {
    val lat: String
        get() = (37.491891055924896 + _lat.toDouble().rem(0.01)).toString()

    val lng: String
        get() = (127.00772018475332 + _lng.toDouble().rem(0.01)).toString()
}
