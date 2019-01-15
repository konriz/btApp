package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.modules.abstraction.Driver
import pl.com.tt.kapp.modules.abstraction.Presentable
import pl.com.tt.kapp.modules.abstraction.ScanResultListener
import pl.com.tt.kapp.modules.abstraction.ScanResultsList
import pl.com.tt.kapp.utils.DtoListConverter

object BluetoothDriver : Driver<BluetoothDevice>(adapter = BTAdapter), Presentable {

    private var listener : ScanResultListener? = null
    var lastDevices : ScanResultsList = ScanResultsList.EmptyList

    override fun attachPresenter(presenter: ScanResultListener){
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    override fun onDiscoveryStarted(){
        listener?.onDiscoveryStarted()
    }

    override fun onDiscoveryFinished(results : List<BluetoothDevice>){
        lastDevices = DtoListConverter.bluetoothResultsToDto(results)
        listener?.onDiscoveryFinished()
    }

}