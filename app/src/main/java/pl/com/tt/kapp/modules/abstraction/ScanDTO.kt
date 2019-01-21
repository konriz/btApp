package pl.com.tt.kapp.modules.abstraction

import java.util.*

abstract class ScanDTO(val list : List<DeviceDTO>, val placeTime: PlaceTime){
    object EmptyList : ScanDTO(listOf(),
        PlaceTime(place = null, time = Date())
    )
}