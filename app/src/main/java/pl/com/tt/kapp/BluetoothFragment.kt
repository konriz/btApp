package pl.com.tt.kapp

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
import kotlinx.android.synthetic.main.bluetooth_fragment.view.*


class BluetoothFragment : Fragment(), BluetoothMVP.View {

    private val presenter = BluetoothPresenter(this)
    private lateinit var viewAdapter: DevicesListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var devicesList : List<BluetoothDevice> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bluetooth_fragment, container, false)

        activity?.registerReceiver(presenter.getReceiver(), presenter.getReceiver().filter)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = DevicesListAdapter(devicesList)

        view?.devicesListRecycler?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        view?.devicesListRecycler?.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.enableBTswitch?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.enableBluetooth()
            } else {
                presenter.disableBluetooth()
            }
        }

        view?.scanButton?.setOnClickListener {
            presenter.scanDevices()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setBtSwitch()
    }

    override fun showLoader() {
        view?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        view?.progressBar?.visibility = View.GONE
    }

    override fun setSwitch(state: Boolean) {
        view?.enableBTswitch?.isChecked = state
    }

    override fun showToast(message: Int, length : Int) {
        Toast.makeText(activity, message, length).show()
    }

    override fun updateRecycler(devices: List<BluetoothDevice>) {
        devicesList = devices
        viewAdapter.update(devicesList)
    }

    override fun onDestroy() {
        activity?.unregisterReceiver(presenter.driver.receiver)
        presenter.onDestroy()
        super.onDestroy()
    }

}