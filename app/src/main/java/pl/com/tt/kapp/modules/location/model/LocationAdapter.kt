package pl.com.tt.kapp.modules.location.model

import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import pl.com.tt.kapp.modules.NetworkingAdapter

const val TAG = "GPS-Adapter"

object GPSAdapter : NetworkingAdapter {

    lateinit var gpsService : FusedLocationProviderClient

    override fun enable() {
    }

    override fun disable() {
    }

    override fun isEnabled(): Boolean{
        return false
    }

    override fun scan() {
        getLastLocation()
    }

    private fun getLastLocation() {
        try{
            gpsService.lastLocation.addOnSuccessListener {
                LocationDriver.updateLocation(it)
                Log.i(TAG, "Last location passed to driver")
            }
        } catch (e : SecurityException){
            Log.e(TAG, "Location permission denied!")
        }
    }


}
