package pl.com.tt.kapp.modules.wifi.view

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
import pl.com.tt.kapp.modules.wifi.WifiMVP
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO
import pl.com.tt.kapp.modules.wifi.presenter.WifiPresenter

class WifiFragment : Fragment(), WifiMVP.View {

    private val presenter = WifiPresenter(this)
    private lateinit var viewAdapter: NetworksListAdapter
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wifi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = NetworksListAdapter(listOf())

        networksRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        networksRecycler.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.enableWifi()
            } else {
                presenter.disableWifi()
            }
        }

        wifiScanButton.setOnClickListener {
            presenter.scanNetworks()
        }
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun setSwitch(state: Boolean) {
        wifiSwitch.isChecked = state
    }

    override fun showToast(message: Int, length: Int) {
        Toast.makeText(activity, message, length).show()
    }

    override fun updateRecycler(networks: List<WifiNetworkDTO>) {
        TODO("Implement me")
    }
}