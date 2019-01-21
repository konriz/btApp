package pl.com.tt.kapp.modules.model.bluetooth

import android.location.Location
import pl.com.tt.kapp.modules.abstraction.PlaceTime
import pl.com.tt.kapp.modules.abstraction.ScanDTO
import java.util.*

class BluetoothDTO(devices : List<BluetoothDeviceDTO>, location : Location?) : ScanDTO(devices,
    PlaceTime(location, Date())
)