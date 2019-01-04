package pl.com.tt.kapp.modules.wifi.presenter

import pl.com.tt.kapp.modules.wifi.WifiMVP
import pl.com.tt.kapp.modules.wifi.model.WifiDriver
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO

class WifiPresenter(var view : WifiMVP.View?) : WifiMVP.Presenter, WifiMVP.ScanResultListener {

    init {
        WifiDriver.attachPresenter(this)
    }

    override fun setWifiSwitch() {
        view?.setSwitch(true)
    }

    fun enableWifi(){
        WifiDriver.enable()
    }

    fun disableWifi(){
        WifiDriver.disable()
    }

    fun scanNetworks(){
        WifiDriver.scan()
    }

    fun onDestroy(){
        view = null
        WifiDriver.detachPresenter()
    }

    override fun onDiscoveryStarted() {
        view?.showLoader()
    }

    override fun onDiscoveryFinished(networks: List<WifiNetworkDTO>) {
        view?.hideLoader()
        view?.updateRecycler(networks)
    }
}