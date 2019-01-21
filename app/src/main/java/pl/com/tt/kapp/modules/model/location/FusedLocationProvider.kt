package pl.com.tt.kapp.modules.model.location

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import pl.com.tt.kapp.modules.abstraction.LocationProvider

private const val TAG = "Location Provider"

object FusedLocationProvider : LocationProvider {

    lateinit var gpsService : FusedLocationProviderClient
    private val locationCallback : LocationCallback
    var lastLocation : Location? = null

    init {
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                onLocationFound(locationResult.lastLocation)
            }
        }
    }

    override fun start() {
        Log.i(TAG, "Location started")
        onStartLocationUpdates()
    }

    override fun stop(){
        Log.i(TAG, "Location stopped")
        onStopLocationUpdates()
    }

    private fun onStartLocationUpdates(){
        val request = LocationRequest.create().setInterval(10000).setFastestInterval(5000).setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        try {
            gpsService.requestLocationUpdates(request,
                locationCallback, null)
        } catch (e : SecurityException){
            e.printStackTrace()
        }
    }

    private fun onLocationFound(location : Location){
        Log.i(TAG, location.toString())
        lastLocation = location
    }

    private fun onStopLocationUpdates(){
        gpsService.removeLocationUpdates(locationCallback)
    }
}