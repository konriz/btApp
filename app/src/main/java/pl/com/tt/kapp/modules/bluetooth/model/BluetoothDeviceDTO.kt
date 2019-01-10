package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.DeviceDTO

data class BTDeviceDTO(val btDevice : BluetoothDevice) : DeviceDTO(btDevice.name, btDevice.address, null)