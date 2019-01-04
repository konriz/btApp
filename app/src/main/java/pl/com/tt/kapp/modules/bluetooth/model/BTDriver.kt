package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP

object BTDriver : Driver, BluetoothMVP.Presentable, BluetoothMVP.ScanResultListener {

    private var listener : BluetoothMVP.ScanResultListener? = null
    var lastDevices : List<BluetoothDevice> = listOf()

    override fun attachPresenter(presenter: BluetoothMVP.ScanResultListener){
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    override fun onDiscoveryStarted(){
        listener?.onDiscoveryStarted()
    }

    override fun onDiscoveryFinished(devices : List<BluetoothDevice>){
        lastDevices = devices
        listener?.onDiscoveryFinished(lastDevices)
    }

    override fun scan() = BTAdapter.scanDevices(this)

    override fun enable() = BTAdapter.enableBluetooth(this)

    override fun disable() = BTAdapter.disableBluetooth(this)

    override fun isEnabled() = BTAdapter.isEnabled()

}