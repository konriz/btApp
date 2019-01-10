package pl.com.tt.kapp.modules.bluetooth.presenter

import android.bluetooth.BluetoothDevice
import android.widget.Toast
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.bluetooth.model.BTDeviceDTO
import pl.com.tt.kapp.modules.bluetooth.model.BluetoothDriver

class BluetoothPresenter(var view : BluetoothMVP.View) : BluetoothMVP.Presenter,
    BluetoothMVP.ScanResultListener {

    init {
        BluetoothDriver.attachPresenter(this)
        view.updateRecycler(convertToDto(BluetoothDriver.lastDevices))
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

    private fun convertToDto(devices: List<BluetoothDevice>) : List<BTDeviceDTO>{
        val devicesDtos = mutableListOf<BTDeviceDTO>()
        for(device in devices){
            devicesDtos.add(BTDeviceDTO(device))
        }
        return devicesDtos.toList()
    }

    override fun onScanButtonPressed() {
        if(BluetoothDriver.isEnabled()){
            BluetoothDriver.scan()
        } else {
            view.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onDiscoveryStarted() {
        view.showToast(R.string.bluetooth_scanning, Toast.LENGTH_SHORT)
        view.showLoader()
    }

    override fun onDiscoveryFinished(devices: List<BluetoothDevice>) {
        view.updateRecycler(convertToDto(devices))
        view.hideLoader()
    }

    override fun onDestroy(){
//        Uncomment this in case of memory leaks
//        view = null
        BluetoothDriver.detachPresenter()
    }
}

