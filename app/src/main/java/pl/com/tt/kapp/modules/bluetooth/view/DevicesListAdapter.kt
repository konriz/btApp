package pl.com.tt.kapp.modules.bluetooth.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.device_row.view.*
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.R

class DevicesListAdapter(private var devices : ScanResultsList)
    : RecyclerView.Adapter<DevicesListAdapter.DevicesListViewHolder>() {

    class DevicesListViewHolder(val deviceRowView : View) : RecyclerView.ViewHolder(deviceRowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DevicesListViewHolder {
        val deviceRowView = LayoutInflater.from(p0.context).inflate(R.layout.device_row, p0, false) as View
        return DevicesListViewHolder(deviceRowView)
    }

    override fun onBindViewHolder(p0: DevicesListViewHolder, p1: Int) {
        p0.deviceRowView.name.text = devices.list[p1].name
        p0.deviceRowView.address.text = devices.list[p1].address
    }

    override fun getItemCount() = devices.list.size

    fun update(devices : ScanResultsList){
        this.devices = devices
        notifyDataSetChanged()
    }
}