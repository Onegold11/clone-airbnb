package com.clonecoding.clone_airbnb.data


import com.google.gson.annotations.SerializedName

data class HouseDtoItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("items")
    val items: List<HouseItem>
)