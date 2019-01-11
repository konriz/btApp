package pl.com.tt.kapp.modules.wifi.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.device_row.view.*
import pl.com.tt.kapp.modules.ScanResultsList
import pl.com.tt.kapp.R

class NetworksListAdapter(private var networks : ScanResultsList)
    : RecyclerView.Adapter<NetworksListAdapter.NetworksListViewHolder>() {

    class NetworksListViewHolder(val networkRowView: View) : RecyclerView.ViewHolder(networkRowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NetworksListAdapter.NetworksListViewHolder {
        val networkRowView = LayoutInflater.from(p0.context).inflate(R.layout.device_row, p0, false)
        return NetworksListViewHolder(networkRowView)
    }

    override fun onBindViewHolder(p0: NetworksListViewHolder, p1: Int) {
        p0.networkRowView.name.text = networks.list[p1].name
        p0.networkRowView.address.text = networks.list[p1].address
    }

    override fun getItemCount(): Int {
        return networks.list.size
    }

    fun update(newNetworks : ScanResultsList){
        networks = newNetworks
        notifyDataSetChanged()
    }

}