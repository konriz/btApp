package pl.com.tt.kapp.modules.wifi.model

import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.NetworkingAdapter
import pl.com.tt.kapp.modules.wifi.WifiMVP

object WifiDriver : Driver(), WifiMVP.ScanResultListener, WifiMVP.Presentable {

    override var adapter: NetworkingAdapter
        get() = WifiAdapter
        set(value) {}

    private var listener : WifiMVP.ScanResultListener? = null
    var lastNetworks : List<WifiNetworkDTO> = listOf()

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

    override fun onDiscoveryFinished(networks: List<WifiNetworkDTO>) {
        lastNetworks = networks
        listener?.onDiscoveryFinished(lastNetworks)
    }
}