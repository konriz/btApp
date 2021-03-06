package pl.com.tt.kapp.modules.vp.saved.bluetooth.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.saved_bluetooth_fragment.*
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.model.persistence.Scan
import pl.com.tt.kapp.modules.model.persistence.ScanViewModel
import pl.com.tt.kapp.modules.vp.saved.SavedScansListAdapter

class SavedBluetoothFragment : Fragment() {

    private lateinit var viewAdapter : SavedScansListAdapter
    private lateinit var viewManager : RecyclerView.LayoutManager
    private lateinit var mScanViewModel : ScanViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        viewAdapter = SavedScansListAdapter(listOf())
        mScanViewModel = ViewModelProviders.of(this).get(ScanViewModel::class.java)
        mScanViewModel.bluetoothScans().observe(this, Observer<List<Scan>> {
            viewAdapter.update(it!!)
        })

        scansListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        clearListButton.setOnClickListener {
            mScanViewModel.deleteAll()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.saved_bluetooth_fragment, container, false)
    }
}