package pl.com.tt.kapp.modules.wifi.model

import android.location.Location
import pl.com.tt.kapp.modules.abstraction.PlaceTime
import pl.com.tt.kapp.modules.abstraction.ScanResultsList
import java.util.*

class WifiResultsList(networks : List<WifiNetworkDTO>, location: Location?) : ScanResultsList(networks,
    PlaceTime(location, Date())
)