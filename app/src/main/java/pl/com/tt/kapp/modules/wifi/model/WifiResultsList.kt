package pl.com.tt.kapp.modules.wifi.model

import android.location.Location
import pl.com.tt.kapp.modules.PlaceTime
import pl.com.tt.kapp.modules.ScanResultsList
import java.util.*

class WifiResultsList(networks : List<WifiNetworkDTO>, location: Location?) : ScanResultsList(networks, PlaceTime(location, Date()))