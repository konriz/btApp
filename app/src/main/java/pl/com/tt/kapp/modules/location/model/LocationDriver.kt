package pl.com.tt.kapp.modules.location.model

import android.location.Location
import pl.com.tt.kapp.modules.Driver

object LocationDriver : Driver<Location>(adapter = LocationAdapter){

    var lastLocation : Location? = null

    fun updateLocation(location : Location?){
        lastLocation = location
    }

    override fun onDiscoveryStarted() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDiscoveryFinished(results: List<Location>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}