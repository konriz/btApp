package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothDevice

class BluetoothMVP{

    interface Presenter{
        fun setBtSwitch()
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
        fun updateRecycler(devices : List<BluetoothDevice>)
    }

    interface Presentable{
        fun attachPresenter(presenter : ScanResultListener)
        fun detachPresenter()
    }

}
