package pl.com.tt.kapp.modules.bluetooth.model

import android.location.Location
import pl.com.tt.kapp.ScanResultsList

class BluetoothResultsList(devices : List<BluetoothDeviceDTO>, location : Location?) : ScanResultsList(devices, location)