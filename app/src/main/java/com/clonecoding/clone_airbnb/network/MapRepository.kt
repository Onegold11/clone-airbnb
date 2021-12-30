package com.clonecoding.clone_airbnb.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clonecoding.clone_airbnb.data.HouseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MapRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://61cc0881198df60017aebe33.mockapi.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HouseService::class.java)

    fun getHouseListFromAPI(houseDto: MutableLiveData<HouseDto>) {

        this.retrofit.getHouseList().enqueue(object : Callback<HouseDto> {
            override fun onResponse(call: Call<HouseDto>, response: Response<HouseDto>) {

                if (response.isSuccessful.not()) {
                    return
                }
                response.body()?.let {
                    Log.d("Retrofit", it.toString())
                    houseDto.value = it
                }
            }

            override fun onFailure(call: Call<HouseDto>, t: Throwable) {
                Log.e("Retrofit", t.message + "")
            }
        })
    }
}
