package pl.com.tt.kapp

import android.location.Location
import pl.com.tt.kapp.modules.DeviceDTO

abstract class ScanResultsList(val list : List<DeviceDTO>, val location : Location?){
    object EmptyList : ScanResultsList(listOf(), null)
}