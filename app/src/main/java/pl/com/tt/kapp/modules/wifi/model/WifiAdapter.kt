package pl.com.tt.kapp.modules.wifi.model

import android.net.wifi.WifiManager
import android.util.Log
import pl.com.tt.kapp.modules.NetworkingAdapter

private const val TAG = "Wifi-Adapter"
object WifiAdapter : NetworkingAdapter {

    lateinit var wifiService : WifiManager

    override fun enable(){
        wifiService.isWifiEnabled = true
        Log.i(TAG, "Wifi Enabled")
    }

    override fun disable(){
        wifiService.isWifiEnabled = false
        Log.i(TAG, "Wifi Disabled")
    }

    override fun scan(){
        /**
         * @deprecated - this will be removed in further APIS and converted to WifiScanner class
         */
        WifiDriver.onDiscoveryStarted()
        wifiService.startScan()
        Log.i(TAG, "Wifi Scanning")
    }

    override fun isEnabled() : Boolean{
        return wifiService.isWifiEnabled
    }

    fun onScanResultsAvailable(){
        val results = wifiService.scanResults
        WifiDriver.onDiscoveryFinished(results)
        Log.i(TAG, results.size.toString())
    }

}