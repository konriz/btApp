package pl.com.tt.kapp.modules.wifi.presenter

import android.net.wifi.ScanResult
import android.widget.Toast
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.location.model.LocationDriver
import pl.com.tt.kapp.modules.wifi.WifiMVP
import pl.com.tt.kapp.modules.wifi.model.WifiDriver
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO
import pl.com.tt.kapp.modules.wifi.model.WifiResultsList

class WifiPresenter(var view : WifiMVP.View) : WifiMVP.Presenter, WifiMVP.ScanResultListener {

    init {
        WifiDriver.attachPresenter(this)

        val lastNetworks = WifiDriver.lastNetworks
        if(lastNetworks.isNotEmpty()){
            updateData(convertToDto(WifiDriver.lastNetworks))
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

    private fun convertToDto(networks : List<ScanResult>) : ScanResultsList {
        val dtos = mutableListOf<WifiNetworkDTO>()
        for(network in networks){
            dtos.add(WifiNetworkDTO(network))
        }

        return WifiResultsList(dtos.toList(), LocationDriver.lastLocation)
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

    override fun onDiscoveryFinished(networks: List<ScanResult>) {
        view.hideLoader()
        updateData(convertToDto(networks))
    }

    private fun updateData(networks : ScanResultsList){
        view.setDateText(networks.placeTime.time.toString())
        view.setLocationText(networks.placeTime.place?.toString())
        view.updateRecycler(networks.list)

    }

    override fun onDestroy(){
//        Uncomment this in case of memory leaks
//        view = null
        WifiDriver.detachPresenter()
    }
}