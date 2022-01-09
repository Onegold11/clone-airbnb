package com.clonecoding.clone_airbnb.network

import com.clonecoding.clone_airbnb.data.HouseDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://61cc0881198df60017aebe33.mockapi.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HouseService::class.java)

    /**
     * 건물 리스트 요청
     */
    suspend fun getHouseListFromAPI(): Response<HouseDto> = withContext(dispatcher) {
        this@MapRepository.retrofit.getHouseList()
    }
}
