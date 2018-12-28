package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import pl.com.tt.kapp.BluetoothPresenter

class BTReceiver(val driver: BTDriver) : BroadcastReceiver() {

    private val devices : MutableSet<BluetoothDevice> = mutableSetOf()
    val filter = IntentFilter()
    private val TAG : String = "BT-Receiver"

    init {
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action : String? = intent.action
        when(action){
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                Log.i(TAG, "Discovery started")
                driver.discoveryStarted()
                devices.clear()
            }

            BluetoothDevice.ACTION_FOUND -> {
                val device : BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                devices.add(device)
            }

            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                Log.i(TAG,"Discovery finished")
                Log.i(TAG,"Devices : $devices")
                driver.discoveredDevices(devices.toList())
            }
        }
    }

}