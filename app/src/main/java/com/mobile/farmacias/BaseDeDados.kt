package com.mobile.farmacias.data

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.mobile.farmacias.R
import com.mobile.farmacias.model.Farmacias
import java.util.*

class BaseDeDados {
    companion object {
        fun carregarFarmacias(context: Context): List<Farmacias> {
            val farmacias = mutableListOf<Farmacias>()
            listOf(R.array.pague_menos, R.array.extra_frama, R.array.drogasil)
                .forEach { storeNameId ->
                    Arrays.asList(context.resources.getStringArray(storeNameId))
                        .forEach {
                            it.forEach {
                                val position = it.toString().split(",")
                                val latlng = LatLng(position[0].toDouble(), position[1].toDouble())
                                val farmacia = Farmacias(
                                    context.resources.getResourceEntryName(storeNameId),
                                    latlng
                                )
                                farmacias.add(farmacia)
                            }
                        }
                }
            return farmacias
        }
    }
}
