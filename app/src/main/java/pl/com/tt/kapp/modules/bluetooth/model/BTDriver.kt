package pl.com.tt.kapp.modules.bluetooth.model

import pl.com.tt.kapp.modules.bluetooth.presenter.BluetoothPresenter
import pl.com.tt.kapp.modules.Driver

class BTDriver (val presenter : BluetoothPresenter) : Driver {
    private val adapter = BTAdapter()
    val receiver = BTReceiver(presenter)

    override fun scan() = adapter.scanDevices(this)

    override fun enable() = adapter.enableBluetooth(this)

    override fun disable() = adapter.disableBluetooth(this)

    override fun isEnabled() = adapter.isEnabled()

}