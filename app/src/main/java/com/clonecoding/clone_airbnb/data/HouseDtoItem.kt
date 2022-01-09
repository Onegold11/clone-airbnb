package com.clonecoding.clone_airbnb.data

import com.google.gson.annotations.SerializedName

data class HouseDtoItem(

    @SerializedName("id")
    val id: String,

    @SerializedName("imgUrl")
    val imgUrl: String,

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("title")
    val title: String
)
