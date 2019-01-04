package pl.com.tt.kapp.modules.wifi.model

import pl.com.tt.kapp.modules.Driver
import pl.com.tt.kapp.modules.wifi.WifiMVP


object WifiDriver : Driver, WifiMVP.ScanResultListener, WifiMVP.Presentable {

    private var listener : WifiMVP.ScanResultListener? = null
    var lastNetworks : List<WifiNetworkDTO> = listOf()

    override fun enable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEnabled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scan() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDiscoveryStarted() {
        lastNetworks = listOf()
        listener?.onDiscoveryStarted()
    }

    override fun onDiscoveryFinished(networks: List<WifiNetworkDTO>) {
        lastNetworks = networks
        listener?.onDiscoveryFinished(lastNetworks)
    }

    override fun attachPresenter(presenter: WifiMVP.ScanResultListener) {
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }
}