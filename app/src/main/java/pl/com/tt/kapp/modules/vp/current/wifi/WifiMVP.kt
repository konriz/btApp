package pl.com.tt.kapp.modules.vp.current.wifi

import pl.com.tt.kapp.modules.abstraction.ScanDTO

class WifiMVP{

    interface Presenter{
        fun onScanButtonPressed()
        fun setWifiSwitch(state : Boolean)
        fun onWifiSwitch(state : Boolean)
        fun onDestroy()
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun switchOn() : Boolean
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateData(networks : ScanDTO)
    }

}
