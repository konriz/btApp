package pl.com.tt.kapp.modules.vp.current.bluetooth.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.current_bluetooth_fragment.*
import pl.com.tt.kapp.modules.abstraction.ScanDTO
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.vp.current.ScanResultsListAdapter
import pl.com.tt.kapp.modules.vp.current.bluetooth.BluetoothMVP
import pl.com.tt.kapp.modules.model.bluetooth.BTReceiver
import pl.com.tt.kapp.modules.vp.current.bluetooth.presenter.BluetoothPresenter
import java.lang.IllegalArgumentException

private const val TAG = "CurrBtFragment"

class CurrentBluetoothFragment : Fragment(), BluetoothMVP.View {

    private lateinit var presenter : BluetoothPresenter
    private lateinit var viewAdapter: ScanResultsListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = ScanResultsListAdapter(ScanDTO.EmptyList.list)

        return inflater.inflate(R.layout.current_bluetooth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BluetoothPresenter(this)
        activity?.registerReceiver(BTReceiver, BTReceiver.filter)

        scansListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        scansListRecycler.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

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

    override fun updateData(results: ScanDTO) {
        viewAdapter.update(results.list)
        bluetoothDateText.text = results.placeTime.timeString()
        bluetoothLocationText.text = results.placeTime.placeString()
    }

    override fun onDestroy() {
        try{
            activity?.unregisterReceiver(BTReceiver)
        } catch (e : IllegalArgumentException){
            Log.w(TAG, "Receiver is not registered")
        }
        presenter.onDestroy()
        super.onDestroy()
    }
}