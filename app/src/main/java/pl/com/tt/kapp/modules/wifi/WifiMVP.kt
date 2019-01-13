package pl.com.tt.kapp.modules.wifi

import pl.com.tt.kapp.modules.ScanResultsList

class WifiMVP{

    interface Presenter{
        fun onScanButtonPressed()
        fun setWifiSwitch(state : Boolean)
        fun onWifiSwitch(state : Boolean)
        fun onDestroy()
    }

    interface ScanResultListener{
        fun onInterfaceEnabled()
        fun onInterfaceDisabled()
        fun onDiscoveryStarted()
        fun onDiscoveryFinished()
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun switchOn() : Boolean
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateData(networks : ScanResultsList)
    }

    interface Presentable{
        fun attachPresenter(presenter : WifiMVP.ScanResultListener)
        fun detachPresenter()
    }

}
