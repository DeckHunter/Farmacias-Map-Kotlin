package com.mobile.farmacias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.mobile.farmacias.data.DataSource
import com.mobile.farmacias.databinding.ActivityMapsBinding
import com.mobile.farmacias.model.DrugStore

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var drugStores: List<DrugStore>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drugStores = DataSource.loadDrugStores(applicationContext)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        drugStores.forEach {
            mMap.addMarker(MarkerOptions()
                .position(it.position)
                .title(it.name))
        }

        googleMap.setOnMapLoadedCallback {
            val bounds = LatLngBounds.builder()
            drugStores.forEach { bounds.include(it.position) }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
        }
    }
}