package pl.com.tt.kapp

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.GravityCompat
import android.util.Log
import android.view.MenuItem
import androidx.room.Room
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.main_layout.*
import pl.com.tt.kapp.modules.model.location.FusedLocationProvider
import pl.com.tt.kapp.modules.model.persistence.ScanDatabase
import pl.com.tt.kapp.modules.vp.current.CurrentScansFragment
import pl.com.tt.kapp.modules.vp.saved.SavedScansFragment


const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {

    private val database = Room
        .databaseBuilder(applicationContext, ScanDatabase::class.java, "scan-database")
        .build()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

//        Toolbar part
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

//        Navigation view part
        if(fragmentContainer != null){
            if(savedInstanceState == null){
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, CurrentScansFragment())
                    .commit()
            }
        }

        val navigationView = navigation_view
        navigationView.setNavigationItemSelectedListener { 
            menuItem ->
            when(menuItem.itemId) {
                R.id.nav_current -> {
                    if(menuItem.isChecked){
                        Log.i(TAG, "Current view is already on")
                    } else {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, CurrentScansFragment())
                            .commit()
                    }
                }
                R.id.nav_saved -> {
                    if(menuItem.isChecked) {
                        Log.i(TAG, "Saved view is already on")
                    } else {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, SavedScansFragment())
                            .commit()
                    }
                }
            }
            menuItem.isChecked = true
            drawer_layout.closeDrawers()
            true
        }

//        Services part
        FusedLocationProvider.gpsService = LocationServices.getFusedLocationProviderClient(this)

//        Permissions part
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            askForPermissions()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
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
