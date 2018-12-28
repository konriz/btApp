package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.BluetoothPresenter

class BTDriver (val presenter : BluetoothPresenter){
    private val adapter = BTAdapter()
    val receiver = BTReceiver(this)

    fun discoveryStarted() {
        presenter.showLoader()
    }

    fun discoveredDevices(devices : List<BluetoothDevice>) {
        presenter.updateDevices(devices)
    }

    fun enableBluetooth() = adapter.enableBluetooth()

    fun disableBluetooth() = adapter.disableBluetooth()

    fun bluetoothEnabled() = adapter.enabled

    fun scanDevices() = adapter.scanDevices()

}