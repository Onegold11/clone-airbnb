package com.clonecoding.clone_airbnb

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.clonecoding.clone_airbnb.adapter.HouseViewPagerAdapter
import com.clonecoding.clone_airbnb.constants.MapConstants
import com.clonecoding.clone_airbnb.data.HouseDto
import com.clonecoding.clone_airbnb.databinding.ActivityMainBinding
import com.clonecoding.clone_airbnb.viewmodel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons

class MainActivity : AppCompatActivity() {

    /**
     * Naver map object
     */
    private lateinit var naverMap: NaverMap

    /**
     * Naver map locationSource
     */
    private lateinit var locationSource: FusedLocationSource

    /**
     * 뷰 모델
     */
    private val viewModel: MainViewModel by viewModels()

    /**
     * 뷰 페이저 어댑터
     */
    private val viewPagerAdapter = HouseViewPagerAdapter()

    /**
     * Naver map ready callback
     */
    private val mapReadyCallback = OnMapReadyCallback {
        naverMap = it
        naverMap.maxZoom = MapConstants.MAX_ZOOM
        naverMap.minZoom = MapConstants.MIN_ZOOM

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.49191767667589, 127.00769456278235))
        naverMap.moveCamera(cameraUpdate)

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = true
        uiSetting.isCompassEnabled = true
        uiSetting.isScaleBarEnabled = true
        uiSetting.isZoomControlEnabled = true

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = this.locationSource

        this.viewModel.requestHouseList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
            }
        mapFragment.getMapAsync(this.mapReadyCallback)

        binding.houseViewPager.adapter = this.viewPagerAdapter

        this.initViewModel()
    }

    /**
     * 뷰 모델 초기화
     */
    private fun initViewModel() {

        this.viewModel.houseList.observe(this, {
            updateMarker(it)
            viewPagerAdapter.submitList(it)
        })
    }

    /**
     * 지도 마커 업데이트
     */
    private fun updateMarker(dto: HouseDto) {
        dto.forEach { house ->
            val marker = Marker().apply {

                position = LatLng(house.lat.toDouble(), house.lng.toDouble())
                map = naverMap
                tag = house.id
                icon = MarkerIcons.BLACK
                iconTintColor = Color.RED
            }
        }
    }

    /**
     * 권한 요청
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (this.locationSource.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        ) {
            if (!this.locationSource.isActivated) {
                this.naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
