package pl.com.tt.kapp.modules.model.wifi

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.abstraction.DeviceDTO
import pl.com.tt.kapp.modules.abstraction.ScanType

data class WifiNetworkDTO(val network : ScanResult) : DeviceDTO(network.SSID, network.BSSID, ScanType.WIFI)