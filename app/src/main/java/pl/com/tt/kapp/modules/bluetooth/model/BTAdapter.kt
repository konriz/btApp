package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothAdapter
import android.util.Log

class BTAdapter{
    private val mBluetoothAdapter : BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val TAG : String = "BT-Adapter"

    fun enableBluetooth(driver : BTDriver) {
        if(mBluetoothAdapter != null && !isEnabled())
        {
            mBluetoothAdapter.enable()
            Log.i(TAG, "Bluetooth enabled")
        }
    }

    fun disableBluetooth(driver : BTDriver) {
        if (mBluetoothAdapter != null && isEnabled()){
            mBluetoothAdapter.disable()
            Log.i(TAG, "Bluetooth disabled")
        }
    }

    fun scanDevices(driver : BTDriver) {
        if(mBluetoothAdapter != null && isEnabled())
        {
            mBluetoothAdapter.startDiscovery()
            Log.i(TAG, "Starting discovery")
        }
    }

    fun isEnabled() : Boolean{
        if (mBluetoothAdapter == null){
            return false
        }
        return mBluetoothAdapter.isEnabled
    }

}