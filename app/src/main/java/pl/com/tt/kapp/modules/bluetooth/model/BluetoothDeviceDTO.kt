package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.abstraction.DeviceDTO

data class BluetoothDeviceDTO(val btDevice : BluetoothDevice) : DeviceDTO(btDevice.name, btDevice.address)