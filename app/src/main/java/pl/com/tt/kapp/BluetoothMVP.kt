package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice

class BluetoothMVP{
    interface View{
        fun showLoader()
        fun hideLoader()
        fun showToast(message : Int, length : Int)
        fun updateRecycler(devices : List<BluetoothDevice>)
    }

    interface Listener{
    }
}
