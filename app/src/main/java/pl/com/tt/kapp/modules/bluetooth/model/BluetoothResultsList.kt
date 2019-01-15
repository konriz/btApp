package pl.com.tt.kapp.modules.bluetooth.model

import android.location.Location
import pl.com.tt.kapp.modules.abstraction.PlaceTime
import pl.com.tt.kapp.modules.abstraction.ScanResultsList
import java.util.*

class BluetoothResultsList(devices : List<BluetoothDeviceDTO>, location : Location?) : ScanResultsList(devices,
    PlaceTime(location, Date())
)