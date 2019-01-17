package pl.com.tt.kapp

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.main_layout.*
import pl.com.tt.kapp.modules.location.model.FusedLocationProvider


const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {
    
    private lateinit var mDrawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
        
        mDrawerLayout = drawer_layout
        val navigationView = navigation_view
        navigationView.setNavigationItemSelectedListener { 
            menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }
        

        FusedLocationProvider.gpsService = LocationServices.getFusedLocationProviderClient(this)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            askForPermissions()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
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
