package pl.com.tt.kapp.modules.model.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Scan(

    @PrimaryKey
    @ColumnInfo(name = "scan_date")
    var date: String,

    @ColumnInfo(name = "devices_count")
    var devicesCount: Int?
)