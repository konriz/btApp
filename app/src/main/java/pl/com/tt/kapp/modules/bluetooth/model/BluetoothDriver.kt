package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.DtoListConverter
import pl.com.tt.kapp.modules.NetworkingAdapter
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP

object BluetoothDriver : Driver(adapter = BTAdapter), BluetoothMVP.Presentable {

    private var listener : BluetoothMVP.ScanResultListener? = null
    var lastDevices : ScanResultsList = ScanResultsList.EmptyList

    override fun attachPresenter(presenter: BluetoothMVP.ScanResultListener){
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    fun onDiscoveryStarted(){
        listener?.onDiscoveryStarted()
    }

    fun onDiscoveryFinished(devices : List<BluetoothDevice>){
        lastDevices = DtoListConverter.bluetoothResultsToDto(devices)
        listener?.onDiscoveryFinished()
    }

}