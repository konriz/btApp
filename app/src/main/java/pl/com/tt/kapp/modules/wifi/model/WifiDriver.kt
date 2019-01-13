package pl.com.tt.kapp.modules.wifi.model

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.DtoListConverter
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.modules.wifi.WifiMVP

object WifiDriver : Driver(adapter = WifiAdapter), WifiMVP.Presentable {

    private var listener : WifiMVP.ScanResultListener? = null
    var lastNetworks : ScanResultsList = ScanResultsList.EmptyList

    override fun attachPresenter(presenter: WifiMVP.ScanResultListener) {
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    fun onDiscoveryStarted() {
        listener?.onDiscoveryStarted()
    }

    fun onDiscoveryFinished(networks: List<ScanResult>) {
        lastNetworks = DtoListConverter.wifiResultsToDto(networks)
        listener?.onDiscoveryFinished()
    }

    fun onInterfaceEnabled(){
        listener?.onInterfaceEnabled()
    }

    fun onInterfaceDisabled(){
        listener?.onInterfaceDisabled()
    }
}