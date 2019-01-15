package pl.com.tt.kapp.modules.wifi.model

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.abstraction.Driver
import pl.com.tt.kapp.modules.abstraction.Presentable
import pl.com.tt.kapp.modules.abstraction.ScanResultListener
import pl.com.tt.kapp.modules.abstraction.ScanResultsList
import pl.com.tt.kapp.utils.DtoListConverter

object WifiDriver : Driver<ScanResult>(adapter = WifiAdapter), Presentable {

    private var listener : ScanResultListener? = null
    var lastNetworks : ScanResultsList = ScanResultsList.EmptyList

    override fun attachPresenter(presenter: ScanResultListener) {
        listener = presenter
    }

    override fun detachPresenter() {
        listener = null
    }

    override fun onDiscoveryStarted() {
        listener?.onDiscoveryStarted()
    }

    override fun onDiscoveryFinished(results : List<ScanResult>) {
        lastNetworks = DtoListConverter.wifiResultsToDto(results)
        listener?.onDiscoveryFinished()
    }

    fun onInterfaceEnabled(){
        listener?.onInterfaceEnabled()
    }

    fun onInterfaceDisabled(){
        listener?.onInterfaceDisabled()
    }
}