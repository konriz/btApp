package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.modules.bluetooth.BTDriver

class BluetoothPresenter(var view : BluetoothMVP.View?) : BluetoothMVP.Presenter, BluetoothMVP.ScanResultListener {

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
        driver.scanDevices()
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

