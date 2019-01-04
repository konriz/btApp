package pl.com.tt.kapp.modules.bluetooth.view

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.bluetooth_fragment.*
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.modules.bluetooth.presenter.BluetoothPresenter
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.bluetooth.model.BTReceiver


class BluetoothFragment : Fragment(), BluetoothMVP.View {

    private lateinit var presenter : BluetoothPresenter
    private lateinit var viewAdapter: DevicesListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = DevicesListAdapter(listOf())
        presenter = BluetoothPresenter(this)
        return inflater.inflate(R.layout.bluetooth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.registerReceiver(BTReceiver, BTReceiver.filter)

        devicesListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        devicesListRecycler.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        enableBTswitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.enableBluetooth()
            } else {
                presenter.disableBluetooth()
            }
        }

        scanButton.setOnClickListener {
            presenter.scanDevices()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setBtSwitch()
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun setSwitch(state: Boolean) {
        enableBTswitch.isChecked = state
    }

    override fun showToast(message: Int, length : Int) {
        Toast.makeText(context, message, length).show()
    }

    override fun updateRecycler(devices: List<BluetoothDevice>) {
        viewAdapter.update(devices)
    }

    override fun onDestroy() {
        activity?.unregisterReceiver(BTReceiver)
        presenter.onDestroy()
        super.onDestroy()
    }
}