package com.mobile.farmacias.data

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.mobile.farmacias.R
import com.mobile.farmacias.model.DrugStore
import java.util.*

class DataSource {
    companion object {
        fun loadDrugStores(context: Context): List<DrugStore> {
            val drugStores = mutableListOf<DrugStore>()
            listOf(R.array.pague_menos, R.array.extra_frama, R.array.drogasil)
                .forEach { storeNameId ->
                    Arrays.asList(context.resources.getStringArray(storeNameId))
                        .forEach {
                            it.forEach {
                                val position = it.toString().split(",")
                                val latlng = LatLng(position[0].toDouble(), position[1].toDouble())
                                val drugStore = DrugStore(
                                    context.resources.getResourceEntryName(storeNameId),
                                    latlng
                                )
                                drugStores.add(drugStore)
                            }
                        }
                }
            return drugStores
        }
    }
}