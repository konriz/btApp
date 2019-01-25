package pl.com.tt.kapp.modules.model.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE

@Entity(
    tableName = "scan_result_join",
    primaryKeys = ["scan_date", "result_address"],
    foreignKeys = [
        ForeignKey (entity = Scan::class,
            parentColumns = ["date"],
            childColumns = ["scan_date"],
            onDelete = CASCADE),
        ForeignKey (entity = ResultDto::class,
            parentColumns = ["address"],
            childColumns = ["result_address"],
            onDelete = CASCADE)
    ])
data class ScanResultJoin(

    @ColumnInfo(name = "scan_date")
    var scanDate : String,

    @ColumnInfo(name = "result_address")
    var resultAddress : String
)