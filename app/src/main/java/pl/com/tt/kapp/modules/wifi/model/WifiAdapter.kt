package pl.com.tt.kapp.modules.wifi.model

import android.util.Log
import pl.com.tt.kapp.modules.NetworkingAdapter

private const val TAG = "Wifi-Adapter"
object WifiAdapter : NetworkingAdapter {

    override fun enable(){
        Log.i(TAG, "Wifi Enabled")
    }

    override fun disable(){
        Log.i(TAG, "Wifi Disabled")
    }

    override fun scan(){
        Log.i(TAG, "Wifi Scanning")
    }

    override fun isEnabled() : Boolean{
        Log.i(TAG, "Is wifi enabled?")
        return false
    }

}