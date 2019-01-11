package pl.com.tt.kapp.modules.bluetooth.model

import android.location.Location
import pl.com.tt.kapp.modules.PlaceTime
import pl.com.tt.kapp.modules.ScanResultsList
import java.util.*

class BluetoothResultsList(devices : List<BluetoothDeviceDTO>, location : Location?) : ScanResultsList(devices, PlaceTime(location, Date()))