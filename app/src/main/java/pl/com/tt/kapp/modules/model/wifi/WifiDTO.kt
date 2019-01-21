package pl.com.tt.kapp.modules.model.wifi

import android.location.Location
import pl.com.tt.kapp.modules.abstraction.PlaceTime
import pl.com.tt.kapp.modules.abstraction.ScanDTO
import java.util.*

class WifiDTO(networks : List<WifiNetworkDTO>, location: Location?) : ScanDTO(networks,
    PlaceTime(location, Date())
)