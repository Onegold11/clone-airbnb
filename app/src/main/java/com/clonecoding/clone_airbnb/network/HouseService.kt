package com.clonecoding.clone_airbnb.network

import com.clonecoding.clone_airbnb.data.HouseDto
import retrofit2.Call
import retrofit2.http.GET

interface HouseService {

    @GET("/house")
    fun getHouseList(): Call<HouseDto>
}