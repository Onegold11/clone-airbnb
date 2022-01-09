package com.clonecoding.clone_airbnb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clonecoding.clone_airbnb.data.HouseDto
import com.clonecoding.clone_airbnb.network.MapRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /**
     * 지도 API 저장소
     */
    private val mapRepository = MapRepository()

    /**
     * API 호출 에러 핸들러
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        throwable.printStackTrace()
    }

    /**
     * 건물 리스트
     */
    val houseList: MutableLiveData<HouseDto> = MutableLiveData()

    /**
     * 건물 리스트 요청
     */
    fun requestHouseList() = viewModelScope.launch(exceptionHandler) {

        val result = this@MainViewModel.mapRepository.getHouseListFromAPI()

        if (result.isSuccessful) {
            this@MainViewModel.houseList.postValue(result.body())
        }
    }
}
