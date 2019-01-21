package pl.com.tt.kapp.modules.model.wifi

import android.net.wifi.WifiManager
import android.util.Log
import pl.com.tt.kapp.modules.abstraction.NetworkingAdapter

private const val TAG = "Wifi-Adapter"
object WifiAdapter : NetworkingAdapter {

    lateinit var wifiService : WifiManager
    var scanPending = false

    override fun enable(){
        wifiService.isWifiEnabled = true
        Log.i(TAG, "Wifi Enabled")
    }

    override fun disable(){
        wifiService.isWifiEnabled = false
        Log.i(TAG, "Wifi Disabled")
    }

    override fun isEnabled() : Boolean{
        return wifiService.isWifiEnabled
    }

    override fun scan(){
        /**
         * @deprecated - this will be removed in further APIS and converted to WifiScanner class
         */
        scanPending = true
        WifiDriver.onDiscoveryStarted()
        wifiService.startScan()
        Log.i(TAG, "Wifi Scanning")
    }

    fun onScanResultsAvailable(){
        if(scanPending){
            val results = wifiService.scanResults
            WifiDriver.onDiscoveryFinished(results)
            Log.i(TAG, results.size.toString())
            scanPending = false
        }
    }

}