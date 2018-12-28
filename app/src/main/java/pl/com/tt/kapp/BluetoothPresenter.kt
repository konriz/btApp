package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.model.BTAdapter
import pl.com.tt.kapp.model.BTReceiver

class BluetoothPresenter(var bluetoothView : BluetoothMVP.View?) {

    private val deviceAdapter = BTAdapter()
    val receiver = BTReceiver(this)

    fun bluetoothEnabled() = deviceAdapter.enabled

    fun enableBluetooth(state : Boolean){
        if(state){
            showBtStatus(deviceAdapter.enableBluetooth())
        } else {
            showBtStatus(deviceAdapter.disableBluetooth())
        }
    }

    fun scanDevices(){
        deviceAdapter.scanDevices()
    }

    fun showLoader(){
        bluetoothView?.showLoader()
    }

    fun hideLoader(){
        bluetoothView?.hideLoader()
    }

    fun showBtStatus(result : Int){
        when(result) {
            -1 -> bluetoothView?.showToast(R.string.no_bluetooth_device, Toast.LENGTH_SHORT)
            0 -> bluetoothView?.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
            1 -> bluetoothView?.showToast(R.string.bluetooth_enabled, Toast.LENGTH_SHORT)
            2 -> bluetoothView?.showToast(R.string.bluetooth_already_enabled, Toast.LENGTH_SHORT)
        }
    }

    fun updateDevices(devices: List<BluetoothDevice>){
        bluetoothView?.updateRecycler(devices)
        hideLoader()
    }

    fun onDestroy(){
        bluetoothView = null
    }


}

