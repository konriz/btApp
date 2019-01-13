package pl.com.tt.kapp.modules.bluetooth.presenter

import android.widget.Toast
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.modules.bluetooth.model.BluetoothDriver
import pl.com.tt.kapp.modules.location.model.LocationDriver

class BluetoothPresenter(var view : BluetoothMVP.View) : BluetoothMVP.Presenter,
    BluetoothMVP.ScanResultListener {

    init {
        BluetoothDriver.attachPresenter(this)

        val lastDevices = BluetoothDriver.lastDevices
        if(lastDevices.list.isNotEmpty()){
            view.updateData(BluetoothDriver.lastDevices)
        }

    }

    override fun onBluetoothSwitch(state: Boolean) {
        if(state){
            BluetoothDriver.enable()
        } else {
            BluetoothDriver.disable()
        }
    }

    override fun setBtSwitch() {
        view.setSwitch(BluetoothDriver.isEnabled())
    }

    override fun onScanButtonPressed() {
        if(BluetoothDriver.isEnabled()){
            LocationDriver.scan()
            BluetoothDriver.scan()
        } else {
            view.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onDiscoveryStarted() {
        view.showToast(R.string.bluetooth_scanning, Toast.LENGTH_SHORT)
        view.showLoader()
    }

    override fun onDiscoveryFinished() {
        view.updateData(BluetoothDriver.lastDevices)
        view.hideLoader()
    }


    override fun onDestroy(){
//        Uncomment this in case of memory leaks
//        view = null
        BluetoothDriver.detachPresenter()
    }
}

