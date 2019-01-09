package pl.com.tt.kapp.modules.wifi.model

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.wifi.WifiMVP

object WifiDriver : Driver(adapter = WifiAdapter), WifiMVP.ScanResultListener, WifiMVP.Presentable {

    private var listener : WifiMVP.ScanResultListener? = null
    var lastNetworks : List<ScanResult> = listOf()

    override fun attachPresenter(presenter: WifiMVP.ScanResultListener) {
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    override fun onDiscoveryStarted() {
        lastNetworks = listOf()
        listener?.onDiscoveryStarted()
    }

    override fun onDiscoveryFinished(networks: List<ScanResult>) {
        lastNetworks = networks
        listener?.onDiscoveryFinished(lastNetworks)
    }

    override fun onInterfaceEnabled(){
        listener?.onInterfaceEnabled()
    }

    override fun onInterfaceDisabled(){
        listener?.onInterfaceDisabled()
    }
}