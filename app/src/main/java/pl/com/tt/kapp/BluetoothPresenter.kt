package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.modules.bluetooth.BTDriver

class BluetoothPresenter(var view : BluetoothMVP.View?) : BluetoothMVP.Presenter {

    val driver = BTDriver(this)

    fun enableBluetooth(){
        showBtStatus(driver.enableBluetooth())
        setBtSwitch()
        }

    fun disableBluetooth(){
        showBtStatus(driver.disableBluetooth())
        setBtSwitch()
    }

    override fun setBtSwitch() {
        view?.setSwitch(driver.bluetoothEnabled())
    }

    fun getReceiver() = driver.receiver

    fun scanDevices() {
        view?.showToast(R.string.bluetooth_scanning, Toast.LENGTH_SHORT)
        driver.scanDevices()
    }

    fun showLoader() = view?.showLoader()

    fun hideLoader() = view?.hideLoader()

    fun showBtStatus(result : Int){
        when(result) {
            -1 -> view?.showToast(R.string.no_bluetooth_device, Toast.LENGTH_SHORT)
            0 -> view?.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
            1 -> view?.showToast(R.string.bluetooth_enabled, Toast.LENGTH_SHORT)
            2 -> view?.showToast(R.string.bluetooth_already_enabled, Toast.LENGTH_SHORT)
        }
    }

    fun updateDevices(devices: List<BluetoothDevice>){
        view?.updateRecycler(devices)
        hideLoader()
    }

    fun onDestroy(){
        view = null
    }
}

