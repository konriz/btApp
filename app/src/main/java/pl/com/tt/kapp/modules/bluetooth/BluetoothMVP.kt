package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothDevice
import pl.com.tt.kapp.ScanResultsList

class BluetoothMVP{

    interface Presenter{
        fun setBtSwitch()
        fun onBluetoothSwitch(state : Boolean)
        fun onScanButtonPressed()
        fun onDestroy()
    }

    interface ScanResultListener{
        fun onDiscoveryStarted()
        fun onDiscoveryFinished(devices : List<BluetoothDevice>)
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateRecycler(devices : ScanResultsList)
    }

    interface Presentable{
        fun attachPresenter(presenter : ScanResultListener)
        fun detachPresenter()
    }

}
