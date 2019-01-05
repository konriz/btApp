package pl.com.tt.kapp.modules.bluetooth.presenter

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.bluetooth.model.BTDriver

class BluetoothPresenter(var view : BluetoothMVP.View?) : BluetoothMVP.Presenter,
    BluetoothMVP.ScanResultListener {

    init {
        BTDriver.attachPresenter(this)
        view?.updateRecycler(BTDriver.lastDevices)
    }

    override fun onBluetoothSwitch(state: Boolean) {
        if(state){
            BTDriver.enable()
        } else {
            BTDriver.disable()
        }
    }

    override fun setBtSwitch() {
        view?.setSwitch(BTDriver.isEnabled())
    }

    fun scanDevices() {
        if(BTDriver.isEnabled()){
            BTDriver.scan()
        } else {
            view?.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onDiscoveryStarted() {
        view?.showToast(R.string.bluetooth_scanning, Toast.LENGTH_SHORT)
        view?.showLoader()
    }

    override fun onDiscoveryFinished(devices: List<BluetoothDevice>) {
        view?.updateRecycler(devices)
        view?.hideLoader()
    }

    override fun onDestroy(){
        view = null
        BTDriver.detachPresenter()
    }
}
