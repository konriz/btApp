package pl.com.tt.kapp.modules.bluetooth.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.current_bluetooth_fragment.*
import pl.com.tt.kapp.modules.abstraction.ScanResultsList
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.ScanResultsListAdapter
import pl.com.tt.kapp.modules.bluetooth.BluetoothMVP
import pl.com.tt.kapp.modules.bluetooth.model.BTReceiver
import pl.com.tt.kapp.modules.bluetooth.presenter.BluetoothPresenter

class CurrentBluetoothFragment : Fragment(), BluetoothMVP.View {

    private lateinit var presenter : BluetoothPresenter
    private lateinit var viewAdapter: ScanResultsListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = ScanResultsListAdapter(ScanResultsList.EmptyList.list)

        return inflater.inflate(R.layout.current_bluetooth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BluetoothPresenter(this)
        activity?.registerReceiver(BTReceiver, BTReceiver.filter)

        devicesListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        devicesListRecycler.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        bluetoothEnableSwitch.setOnCheckedChangeListener { _, isChecked ->
            presenter.onBluetoothSwitch(isChecked)
        }

        bluetoothScanButton.setOnClickListener {
            presenter.onScanButtonPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setBtSwitch()
    }

    override fun showLoader() {
        bluetoothProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        bluetoothProgressBar.visibility = View.GONE
    }

    override fun setSwitch(state: Boolean) {
        bluetoothEnableSwitch.isChecked = state
    }

    override fun showToast(message: Int, length : Int) {
        Toast.makeText(context, message, length).show()
    }

    override fun updateData(results: ScanResultsList) {
        viewAdapter.update(results.list)
        bluetoothDateText.text = results.placeTime.timeString()
        bluetoothLocationText.text = results.placeTime.placeString()
    }

    override fun onDestroy() {
        activity?.unregisterReceiver(BTReceiver)
        presenter.onDestroy()
        super.onDestroy()
    }
}