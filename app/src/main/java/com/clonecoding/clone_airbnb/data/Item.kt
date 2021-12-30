package com.clonecoding.clone_airbnb.data


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String
)