package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.NetworkingAdapter
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP

object BTDriver : Driver(adapter = BTAdapter), BluetoothMVP.Presentable, BluetoothMVP.ScanResultListener {

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

}