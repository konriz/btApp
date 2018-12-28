package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice

class BluetoothMVP{

    interface Presenter{
        fun setBtSwitch()
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateRecycler(devices : List<BluetoothDevice>)
    }

    interface Listener{
    }
}
