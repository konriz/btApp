package pl.com.tt.kapp

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.main_layout.*

private const val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1
private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        }

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter

    }

    private inner class ScreenSlidePagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES
        override fun getItem(p0: Int): Fragment {
            when(p0){
                0 -> return BluetoothFragment()
                1 -> return WifiFragment()
            }
            return BluetoothFragment()
        }
    }

}
