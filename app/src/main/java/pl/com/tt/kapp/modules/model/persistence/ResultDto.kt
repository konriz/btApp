package pl.com.tt.kapp.modules.model.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "result")
data class ResultDto(

    @PrimaryKey
    var address : String,

    var name : String?

)