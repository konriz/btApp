package pl.com.tt.kapp

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : AppCompatActivity() {

    val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        }

    }

}
