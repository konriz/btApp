package pl.com.tt.kapp.modules.vp.current.bluetooth.presenter

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.widget.Toast
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.abstraction.ScanResultListener
import pl.com.tt.kapp.modules.abstraction.ScanType
import pl.com.tt.kapp.modules.vp.current.bluetooth.BluetoothMVP
import pl.com.tt.kapp.modules.model.bluetooth.BluetoothDriver
import pl.com.tt.kapp.modules.model.persistence.ResultDto
import pl.com.tt.kapp.modules.model.persistence.Scan
import pl.com.tt.kapp.modules.model.persistence.ScanViewModel

class BluetoothPresenter(var view : BluetoothMVP.View) : BluetoothMVP.Presenter,
    ScanResultListener {

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
            onInterfaceEnabled()
        } else {
            BluetoothDriver.disable()
            onInterfaceDisabled()
        }
    }

    override fun setBtSwitch() {
        view.setSwitch(BluetoothDriver.isEnabled())
    }

    override fun onScanButtonPressed() {
        if(BluetoothDriver.isEnabled()){
            BluetoothDriver.scan()
        } else {
            view.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onSaveButtonPressed(fragment : Fragment) {
        val mScanViewModel = ViewModelProviders.of(fragment).get(ScanViewModel::class.java)
        val lastScan = BluetoothDriver.lastDevices
        if (lastScan.list.isEmpty()){
            view.showToast(R.string.scan_empty, Toast.LENGTH_SHORT)
        } else {
            val scan = Scan(lastScan.placeTime.timeString(), lastScan.placeTime.placeString(), ScanType.BLUETOOTH.type)
            mScanViewModel.insert(scan, lastScan.list.map { ResultDto(it.address, it.name) })
            view.showToast(R.string.scan_saved, Toast.LENGTH_SHORT)
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

    override fun onInterfaceEnabled() {
//        view.showToast(R.string.bluetooth_enabled, Toast.LENGTH_SHORT)
    }

    override fun onInterfaceDisabled() {
//        view.showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
    }


    override fun onDestroy(){
//        Uncomment this in case of memory leaks
//        view = null
        BluetoothDriver.detachPresenter()
    }
}

