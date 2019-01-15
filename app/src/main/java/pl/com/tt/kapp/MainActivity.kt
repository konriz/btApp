package pl.com.tt.kapp

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.*
import android.util.Log
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.main_layout.*
import pl.com.tt.kapp.modules.location.model.FusedLocationProvider


const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        FusedLocationProvider.gpsService = LocationServices.getFusedLocationProviderClient(this)
        setSupportActionBar(toolbar)

        pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            askForPermissions()
        }
    }

    override fun onResume() {
        super.onResume()
        FusedLocationProvider.start()
    }

    override fun onPause() {
        super.onPause()
        FusedLocationProvider.stop()
    }

    @TargetApi(23)
    private fun askForPermissions(){

        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE)

        for (permission in permissions){
            if(checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission),
                    1)
            }
            Log.i(TAG, "Permission granted : $permission")
        }
    }
}
