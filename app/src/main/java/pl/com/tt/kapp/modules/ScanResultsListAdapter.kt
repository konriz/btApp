package pl.com.tt.kapp.modules

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.device_row.view.*
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.abstraction.DeviceDTO

class ScanResultsListAdapter(private var devices : List<DeviceDTO>)
    : RecyclerView.Adapter<ScanResultsListAdapter.DevicesListViewHolder>() {

    class DevicesListViewHolder(val deviceRowView : View) : RecyclerView.ViewHolder(deviceRowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DevicesListViewHolder {
        val deviceRowView = LayoutInflater.from(p0.context).inflate(R.layout.device_row, p0, false) as View
        return DevicesListViewHolder(deviceRowView)
    }

    override fun onBindViewHolder(p0: DevicesListViewHolder, p1: Int) {

        val device = devices[p1]
        val row = p0.deviceRowView

        row.name.text = device.name
        row.address.text = device.address

    }

    override fun getItemCount() = devices.size

    fun update(devices : List<DeviceDTO>){
        this.devices = devices
        notifyDataSetChanged()
    }
}