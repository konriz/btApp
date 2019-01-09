package pl.com.tt.kapp.modules.wifi.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log

private const val TAG = "Wifi-Receiver"

object WifiReceiver : BroadcastReceiver() {

    val filter = IntentFilter()

    init {
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "Wifi action received")
        when (intent?.action) {
            WifiManager.WIFI_STATE_CHANGED_ACTION -> onWifiStateChanged(intent)
            WifiManager.SCAN_RESULTS_AVAILABLE_ACTION -> onScanResultsAvailable(intent)
        }
    }

    private fun onWifiStateChanged(intent: Intent?) {
        val wifiState = intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1)
        when (wifiState) {
            WifiManager.WIFI_STATE_ENABLED -> onWifiEnabled()
            WifiManager.WIFI_STATE_DISABLED -> onWifiDisabled()
        }
    }

    private fun onWifiEnabled() {
        WifiDriver.onInterfaceEnabled()
    }

    private fun onWifiDisabled() {
        WifiDriver.onInterfaceDisabled()
    }

    private fun onScanResultsAvailable(intent: Intent?) {
        val scanned = intent?.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
        Log.i(TAG, "Scan results available : $scanned")
        WifiAdapter.onScanResultsAvailable()
    }
}