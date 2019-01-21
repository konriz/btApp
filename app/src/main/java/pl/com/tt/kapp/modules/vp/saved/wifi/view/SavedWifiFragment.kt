package pl.com.tt.kapp.modules.vp.saved.wifi.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.vp.saved.SavedScansListAdapter

class SavedWifiFragment : Fragment() {

    private lateinit var viewAdapter : SavedScansListAdapter
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(activity)
        viewAdapter = SavedScansListAdapter(listOf())

        return inflater.inflate(R.layout.saved_wifi_fragment, container, false)
    }
}