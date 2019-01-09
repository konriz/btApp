package pl.com.tt.kapp.modules.wifi

import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO

class WifiMVP{

    interface Presenter{
        fun setWifiSwitch()
        fun onWifiSwitch(state : Boolean)
        fun onDestroy()
    }

    interface ScanResultListener{
        fun onInterfaceEnabled()
        fun onInterfaceDisabled()
        fun onDiscoveryStarted()
        fun onDiscoveryFinished(networks : List<ScanResult>)
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateRecycler(networks : List<WifiNetworkDTO>)
    }

    interface Presentable{
        fun attachPresenter(presenter : WifiMVP.ScanResultListener)
        fun detachPresenter()
    }

}
