package pl.com.tt.kapp

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        setSupportActionBar(toolbar)

        pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        askForPermissions()

    }

    private fun askForPermissions(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE),
        1)
    }

}
