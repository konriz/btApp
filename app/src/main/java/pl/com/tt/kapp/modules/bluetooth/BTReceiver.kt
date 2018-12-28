package pl.com.tt.kapp.modules.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import pl.com.tt.kapp.BluetoothMVP

class BTReceiver(val presenter: BluetoothMVP.ScanResultListener) : BroadcastReceiver() {

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
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> onDiscoveryStarted()
            BluetoothDevice.ACTION_FOUND -> onDeviceFound(intent)
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> onDiscoveryFinished()
        }
    }

    private fun onDiscoveryStarted(){
        Log.i(TAG, "Discovery started")
        presenter.onDiscoveryStarted()
        devices.clear()
    }

    private fun onDeviceFound(intent : Intent){
        val device : BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        devices.add(device)
    }

    private fun onDiscoveryFinished() {
        Log.i(TAG,"Discovery finished")
        Log.i(TAG,"Devices : $devices")
        presenter.onDiscoveryFinished(devices.toList())
    }

}