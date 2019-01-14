package pl.com.tt.kapp.modules

import android.location.Location
import java.lang.StringBuilder
import java.text.DateFormat
import java.util.*

class PlaceTime(val place : Location?, val time : Date){

    fun placeString() : String{
        if (place != null){
            val builder = StringBuilder()
            builder.append("Latitude : ${place.latitude}\n")
            builder.append("Longitude : ${place.longitude}\n")
            builder.append("Altitude : ${place.altitude}")
            return builder.toString()
        }
        return "Not available"
    }

    fun timeString() : String{
        val localFormat = DateFormat.getDateTimeInstance()
        return localFormat.format(time)
    }
}