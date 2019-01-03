package pl.com.tt.kapp

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class ScreenSlidePagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf(BluetoothFragment(), WifiFragment())

    override fun getCount(): Int = fragments.size
    override fun getItem(p0: Int) = fragments[p0]

}
