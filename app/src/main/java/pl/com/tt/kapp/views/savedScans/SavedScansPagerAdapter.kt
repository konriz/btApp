package pl.com.tt.kapp.views.savedScans

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import pl.com.tt.kapp.modules.persistence.SavedBluetoothFragment
import pl.com.tt.kapp.modules.persistence.SavedWifiFragment


class SavedScansPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf(SavedBluetoothFragment(), SavedWifiFragment())

    override fun getCount(): Int = fragments.size
    override fun getItem(p0: Int) = fragments[p0]

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null
        when(position){
            0 -> title = "Saved Bluetooth"
            1 -> title = "Saved Wifi"
        }
        return title
    }

}
