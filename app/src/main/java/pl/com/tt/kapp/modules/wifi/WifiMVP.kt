package pl.com.tt.kapp.modules.wifi

import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO

class WifiMVP{

    interface Presenter{
        fun setWifiSwitch()
    }

    interface ScanResultListener{
        fun onDiscoveryStarted()
        fun onDiscoveryFinished(networks : List<WifiNetworkDTO>)
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
