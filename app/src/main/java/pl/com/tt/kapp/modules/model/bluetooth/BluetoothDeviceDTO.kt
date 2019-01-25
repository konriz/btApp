package pl.com.tt.kapp.modules.model.bluetooth

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.abstraction.DeviceDTO
import pl.com.tt.kapp.modules.abstraction.ScanType

data class BluetoothDeviceDTO(val btDevice : BluetoothDevice) : DeviceDTO(btDevice.name, btDevice.address, ScanType.BLUETOOTH)