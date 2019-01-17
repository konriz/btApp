package pl.com.tt.kapp.modules.abstraction

import java.util.*

abstract class ScanResultsList(val list : List<DeviceDTO>, val placeTime: PlaceTime){
    object EmptyList : ScanResultsList(listOf(),
        PlaceTime(place = null, time = Date())
    )
}