package pl.com.tt.kapp.modules.bluetooth.model

import android.bluetooth.BluetoothAdapter
import android.util.Log
import pl.com.tt.kapp.modules.abstraction.NetworkingAdapter

private const val TAG = "BT-Adapter"

object BTAdapter : NetworkingAdapter {
    private val mBluetoothAdapter : BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    override fun enable() {
        if(mBluetoothAdapter != null && !isEnabled())
        {
            mBluetoothAdapter.enable()
            Log.i(TAG, "Bluetooth enabled")
        }
    }

    override fun disable() {
        if (mBluetoothAdapter != null && isEnabled()){
            mBluetoothAdapter.disable()
            Log.i(TAG, "Bluetooth disabled")
        }
    }

    override fun isEnabled() : Boolean{
        if (mBluetoothAdapter == null){
            return false
        }
        return mBluetoothAdapter.isEnabled
    }

    override fun scan() {
        if(mBluetoothAdapter != null && isEnabled())
        {
            mBluetoothAdapter.startDiscovery()
            Log.i(TAG, "Starting discovery")
        }
    }

}