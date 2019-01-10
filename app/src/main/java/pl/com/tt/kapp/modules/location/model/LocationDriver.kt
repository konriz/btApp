package pl.com.tt.kapp.modules.location.model

import android.location.Location
import pl.com.tt.kapp.modules.Driver

object LocationDriver : Driver(adapter = LocationAdapter){

    var lastLocation : Location? = null

    fun updateLocation(location : Location?){
        lastLocation = location
    }
}