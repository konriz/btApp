package pl.com.tt.kapp.modules.wifi.model

import android.location.Location
import pl.com.tt.kapp.ScanResultsList

class WifiResultsList(networks : List<WifiNetworkDTO>, location: Location?) : ScanResultsList(networks, location) {
}