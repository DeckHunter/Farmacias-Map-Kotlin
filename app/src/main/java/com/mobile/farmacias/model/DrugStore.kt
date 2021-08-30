package com.mobile.farmacias.model

import com.google.android.gms.maps.model.LatLng

data class DrugStore(
    val name: String,
    val position: LatLng
)