package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.device_row.view.*

class DevicesListAdapter(private var devicesList : List<BluetoothDevice>) : RecyclerView.Adapter<DevicesListAdapter.DevicesListViewHolder>() {

    class DevicesListViewHolder(val deviceRowView : View) : RecyclerView.ViewHolder(deviceRowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DevicesListViewHolder {
        val deviceRowView = LayoutInflater.from(p0.context).inflate(R.layout.device_row, p0, false) as View
        return DevicesListViewHolder(deviceRowView)
    }

    override fun onBindViewHolder(p0: DevicesListViewHolder, p1: Int) {
        p0.deviceRowView.deviceName.text = devicesList[p1].name
        p0.deviceRowView.deviceAddress.text = devicesList[p1].address
    }

    override fun getItemCount() = devicesList.size

    fun update(devices : List<BluetoothDevice>){
        devicesList = devices
        notifyDataSetChanged()
    }
}