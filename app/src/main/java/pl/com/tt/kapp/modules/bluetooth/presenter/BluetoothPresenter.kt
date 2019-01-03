package pl.com.tt.kapp.modules.bluetooth.presenter

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.bluetooth.model.BTDriver

class BluetoothPresenter(var view : BluetoothMVP.View?) : BluetoothMVP.Presenter,
    BluetoothMVP.ScanResultListener {

    val driver = BTDriver(this)

    fun enableBluetooth(){
        driver.enable()
    }

    fun disableBluetooth(){
        driver.disable()
    }

    override fun setBtSwitch() {
        view?.setSwitch(driver.isEnabled())
    }

    fun getReceiver() = driver.receiver

    fun scanDevices() {
        if(driver.isEnabled()){
            driver.scan()
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

    fun onDestroy(){
        view = null
    }
}

