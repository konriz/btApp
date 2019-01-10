package pl.com.tt.kapp.modules.gps.model

import android.location.Location
import pl.com.tt.kapp.modules.Driver

object GPSDriver : Driver(adapter = GPSAdapter){

    var lastLocation : Location? = null

    fun updateLocation(location : Location?){
        lastLocation = location


    }
}