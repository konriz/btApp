package pl.com.tt.kapp

import android.bluetooth.BluetoothDevice
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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

        Log.i("MainApp", savedInstanceState?.get("Device").toString())

        registerReceiver(presenter.getReceiver(), presenter.getReceiver().filter)

        viewManager = LinearLayoutManager(this)
        viewAdapter = DevicesListAdapter(devicesList)

        devicesListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        devicesListRecycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        presenter.setBtSwitch()
        enableBTswitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.enableBluetooth()
            } else {
                presenter.disableBluetooth()
            }
        }

        scanButton.setOnClickListener {
            if(enableBTswitch.isChecked){
                presenter.scanDevices()
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

    override fun setSwitch(state: Boolean) {
        enableBTswitch.isChecked = state
    }

    override fun showToast(message: Int, length : Int) {
        Toast.makeText(this, message, length).show()
    }

    override fun updateRecycler(devices: List<BluetoothDevice>) {
        devicesList = devices
        viewAdapter.update(devicesList)
    }

    override fun onDestroy() {
        unregisterReceiver(presenter.driver.receiver)
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if(devicesList.isNotEmpty()){
            outState?.run {
                putString("Device", devicesList[0].name)
            }
        }
        super.onSaveInstanceState(outState)
    }
}
