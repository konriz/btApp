package pl.com.tt.kapp.modules.wifi.model

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.abstraction.DeviceDTO

data class WifiNetworkDTO(val network : ScanResult) : DeviceDTO(network.SSID, network.BSSID)