package pl.com.tt.kapp.modules.wifi.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log

private const val TAG = "Wifi-Receiver"
object WifiReceiver : BroadcastReceiver(){

    private val networks : MutableSet<WifiNetworkDTO> = mutableSetOf()
    val filter = IntentFilter()

    init{
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "Wifi action received")
        val wifiState = intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 1)
        when(wifiState){
            WifiManager.WIFI_STATE_ENABLED -> onWifiEnabled()
            WifiManager.WIFI_STATE_DISABLED -> onWifiDisabled()
        }
    }

    private fun onWifiEnabled(){
        WifiDriver.onInterfaceEnabled()
    }

    private fun onWifiDisabled(){
        WifiDriver.onInterfaceDisabled()
    }
}