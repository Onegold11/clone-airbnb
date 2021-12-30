package com.clonecoding.clone_airbnb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonecoding.clone_airbnb.data.HouseDto
import com.clonecoding.clone_airbnb.network.MapRepository

class MainViewModel: ViewModel() {

    private val mapRepository = MapRepository

    val houseList: MutableLiveData<HouseDto> = MutableLiveData()

    fun requestHouseList() = mapRepository.getHouseListFromAPI(this.houseList)
}
