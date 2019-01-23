package pl.com.tt.kapp.modules.vp.current.wifi.presenter

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.widget.Toast
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.abstraction.ScanResultListener
import pl.com.tt.kapp.modules.model.bluetooth.BluetoothDriver
import pl.com.tt.kapp.modules.model.persistence.Scan
import pl.com.tt.kapp.modules.model.persistence.ScanViewModel
import pl.com.tt.kapp.modules.vp.current.wifi.WifiMVP
import pl.com.tt.kapp.modules.model.wifi.WifiDriver

class WifiPresenter(var view : WifiMVP.View) : WifiMVP.Presenter,
    ScanResultListener {

    init {
        WifiDriver.attachPresenter(this)
        val lastNetworks = WifiDriver.lastNetworks
        if(lastNetworks.list.isNotEmpty()){
            view.updateData(lastNetworks)
        }
    }

    override fun setWifiSwitch(state : Boolean) {
        view.setSwitch(state)
    }

    override fun onWifiSwitch(state: Boolean) {
        if(state){
            WifiDriver.enable()
        } else {
            WifiDriver.disable()
        }
    }

    override fun onSaveButtonPressed(fragment: Fragment) {
        val mScanViewModel = ViewModelProviders.of(fragment).get(ScanViewModel::class.java)
        val lastScan = WifiDriver.lastNetworks
        if (lastScan.list.isEmpty()){
            view.showToast(R.string.scan_empty, Toast.LENGTH_SHORT)
        } else {
            val scan = Scan(lastScan.placeTime.timeString(), lastScan.list.size)
            mScanViewModel.insert(scan)
            view.showToast(R.string.scan_saved, Toast.LENGTH_SHORT)
        }

    }

    override fun onScanButtonPressed(){
        if(WifiDriver.isEnabled()){
            WifiDriver.scan()
            onDiscoveryStarted()
        } else {
            view.showToast(R.string.wifi_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onInterfaceEnabled() {
        if(!view.switchOn()){
            view.setSwitch(true)
        }
//        view.showToast(R.string.wifi_enabled, Toast.LENGTH_SHORT)
    }

    override fun onInterfaceDisabled() {
        if(view.switchOn()){
            view.setSwitch(false)
        }
//        view.showToast(R.string.wifi_disabled, Toast.LENGTH_SHORT)
    }

    override fun onDiscoveryStarted() {
        view.showToast(R.string.wifi_scanning, Toast.LENGTH_SHORT)
        view.showLoader()
    }

    override fun onDiscoveryFinished() {
        view.hideLoader()
        view.updateData(WifiDriver.lastNetworks)
    }

    override fun onDestroy(){
//        Uncomment this in case of memory leaks
//        view = null
        WifiDriver.detachPresenter()
    }
}