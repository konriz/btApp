package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log

class BTAdapter{
    private val mBluetoothAdapter : BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    var enabled : Boolean = mBluetoothAdapter!!.isEnabled

    private val TAG : String = "BT-Adapter"

    fun enableBluetooth() : Int{
        if (mBluetoothAdapter == null){
            return -1
        }
        if (!mBluetoothAdapter.isEnabled){
            enabled = true
            mBluetoothAdapter.enable()
            Log.i(TAG, "Bluetooth enabled")

            return 1
        }
        return 2
    }

    fun disableBluetooth() : Int{
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled){
            enabled = false
            mBluetoothAdapter.disable()
            Log.i(TAG, "Bluetooth disabled")
            return 0
        }
        return -1
    }

    fun scanDevices() {
        mBluetoothAdapter?.startDiscovery()
        Log.i(TAG, "Starting discovery")
    }

    fun getPairedDevices() : List<BluetoothDevice>? {
        return mBluetoothAdapter?.bondedDevices?.toList()
    }

    fun getInfo() : BTDevice?{
        if(mBluetoothAdapter != null){
            return BTDevice(
                name = mBluetoothAdapter.name,
                btAddress = mBluetoothAdapter.address
            )
        }
        return null
    }

}