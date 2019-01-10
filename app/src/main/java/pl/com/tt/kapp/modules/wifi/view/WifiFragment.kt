package pl.com.tt.kapp.modules.wifi.view

import android.content.Context
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.wifi_fragment.*
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.gps.model.GPSAdapter
import pl.com.tt.kapp.modules.wifi.WifiMVP
import pl.com.tt.kapp.modules.wifi.model.WifiAdapter
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO
import pl.com.tt.kapp.modules.wifi.model.WifiReceiver
import pl.com.tt.kapp.modules.wifi.presenter.WifiPresenter

class WifiFragment : Fragment(), WifiMVP.View {

    private lateinit var presenter : WifiPresenter
    private lateinit var viewAdapter: NetworksListAdapter
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = NetworksListAdapter(listOf())
        presenter = WifiPresenter(this)

        return inflater.inflate(R.layout.wifi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        WifiAdapter.wifiService = context?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager

        activity?.registerReceiver(WifiReceiver, WifiReceiver.filter)

        networksRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        networksRecycler.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            presenter.onWifiSwitch(isChecked)
        }

        wifiScanButton.setOnClickListener {
            presenter.onScanButtonPressed()
        }
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun switchOn() = wifiSwitch.isChecked

    override fun setSwitch(state: Boolean) {
        wifiSwitch.isChecked = state
    }

    override fun showToast(message: Int, length: Int) {
        Toast.makeText(context, message, length).show()
    }

    override fun updateRecycler(networks: List<WifiNetworkDTO>) {
        viewAdapter.update(networks)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        activity?.unregisterReceiver(WifiReceiver)
    }
}