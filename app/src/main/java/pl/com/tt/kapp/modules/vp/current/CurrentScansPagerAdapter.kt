package pl.com.tt.kapp.modules.vp.current

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import pl.com.tt.kapp.modules.vp.current.bluetooth.view.CurrentBluetoothFragment
import pl.com.tt.kapp.modules.vp.current.wifi.view.CurrentWifiFragment


class CurrentScansPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

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
