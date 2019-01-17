package pl.com.tt.kapp.views.savedScans

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import pl.com.tt.kapp.modules.bluetooth.view.CurrentBluetoothFragment
import pl.com.tt.kapp.modules.wifi.view.CurrentWifiFragment


class SavedScansPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf(CurrentBluetoothFragment(), CurrentWifiFragment())

    override fun getCount(): Int = fragments.size
    override fun getItem(p0: Int) = fragments[p0]

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null
        when(position){
            0 -> title = "Bluetooth"
            1 -> title = "Wifi"
        }
        return title
    }

}
