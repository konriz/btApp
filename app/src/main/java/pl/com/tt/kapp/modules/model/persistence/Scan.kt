package pl.com.tt.kapp.modules.model.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Scan(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "scan_date") var date: String?,
    @ColumnInfo(name = "devices_count") var devicesCount: String?
)