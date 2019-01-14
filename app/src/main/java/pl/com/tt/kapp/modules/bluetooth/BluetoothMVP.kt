package pl.com.tt.kapp.modules.bluetooth

import pl.com.tt.kapp.modules.ScanResultsList

class BluetoothMVP{

    interface Presenter{
        fun setBtSwitch()
        fun onBluetoothSwitch(state : Boolean)
        fun onScanButtonPressed()
        fun onDestroy()
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateData(results : ScanResultsList)
    }

}
