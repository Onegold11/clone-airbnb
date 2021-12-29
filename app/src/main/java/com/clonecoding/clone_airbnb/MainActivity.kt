package com.clonecoding.clone_airbnb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.clonecoding.clone_airbnb.constants.MapConstants
import com.clonecoding.clone_airbnb.databinding.ActivityMainBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource

class MainActivity : AppCompatActivity() {

    /**
     * Naver map object
     */
    private lateinit var naverMap: NaverMap

    private lateinit var locationSource: FusedLocationSource

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
            }
        mapFragment.getMapAsync(this.mapReadyCallback)
    }

    /**
     *
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (this.locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
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
