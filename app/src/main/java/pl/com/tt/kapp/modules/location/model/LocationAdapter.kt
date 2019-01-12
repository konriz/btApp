package pl.com.tt.kapp.modules.location.model

import android.util.Log
import com.google.android.gms.location.*
import pl.com.tt.kapp.modules.NetworkingAdapter

const val TAG = "GPS-Adapter"

object LocationAdapter : NetworkingAdapter {

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
        try {
            gpsService.lastLocation.addOnSuccessListener {
                LocationDriver.updateLocation(it)
                if (it != null) {
                    Log.i(TAG, it.toString())
                } else {
                    Log.i(TAG, "Location is null")
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Location permission denied!")
        }
    }
}
