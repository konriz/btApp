package pl.com.tt.kapp.modules.model.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "scan")
data class Scan(

    @PrimaryKey
    var date: String,

    var location: String?,

    var type : Int
)