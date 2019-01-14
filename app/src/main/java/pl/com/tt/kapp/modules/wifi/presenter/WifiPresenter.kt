package pl.com.tt.kapp.modules.wifi.presenter

import android.widget.Toast
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.ScanResultListener
import pl.com.tt.kapp.modules.location.model.LocationDriver
import pl.com.tt.kapp.modules.wifi.WifiMVP
import pl.com.tt.kapp.modules.wifi.model.WifiDriver

class WifiPresenter(var view : WifiMVP.View) : WifiMVP.Presenter, ScanResultListener {

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

    override fun onScanButtonPressed(){
        if(WifiDriver.isEnabled()){
            WifiDriver.scan()
            LocationDriver.scan()
            onDiscoveryStarted()
        } else {
            view.showToast(R.string.wifi_disabled, Toast.LENGTH_SHORT)
        }
    }

    override fun onInterfaceEnabled() {
        if(!view.switchOn()){
            view.setSwitch(true)
        }
        view.showToast(R.string.wifi_enabled, Toast.LENGTH_SHORT)
    }

    override fun onInterfaceDisabled() {
        if(view.switchOn()){
            view.setSwitch(false)
        }
        view.showToast(R.string.wifi_disabled, Toast.LENGTH_SHORT)
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