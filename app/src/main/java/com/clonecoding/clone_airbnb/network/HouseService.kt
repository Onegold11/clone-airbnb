package com.clonecoding.clone_airbnb.network

import com.clonecoding.clone_airbnb.data.HouseDto
import retrofit2.Response
import retrofit2.http.GET

interface HouseService {

    @GET("/house")
    suspend fun getHouseList(): Response<HouseDto>
}