package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BluetoothMVP.View {

    private val presenter = BluetoothPresenter(this)

    private lateinit var viewAdapter: DevicesListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var devicesList : List<BluetoothDevice> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(presenter.receiver, presenter.receiver.filter)

        viewManager = LinearLayoutManager(this)
        viewAdapter = DevicesListAdapter(devicesList)

        devicesListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        if(presenter.bluetoothEnabled()){
            enableBTswitch.isChecked = true
        }
        enableBTswitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.enableBluetooth(true)
            } else {
                presenter.enableBluetooth(false)
            }
        }

        scanButton.setOnClickListener {
            if(enableBTswitch.isChecked){
                presenter.scanDevices()
                showToast(R.string.bluetooth_scanning, Toast.LENGTH_SHORT)
            }
            else{
                showToast(R.string.bluetooth_disabled, Toast.LENGTH_SHORT)
            }
        }
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(message: Int, length : Int) {
        Toast.makeText(this, message, length).show()
    }

    override fun updateRecycler(devices: List<BluetoothDevice>) {
        devicesList = devices
        viewAdapter.update(devicesList)
    }

    override fun onDestroy() {
        unregisterReceiver(presenter.receiver)
        presenter.onDestroy()
        super.onDestroy()
    }
}
