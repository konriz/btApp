package pl.com.tt.kapp.modules.model.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Scan::class], version = 1)
abstract class ScanDatabase : RoomDatabase(){
    abstract fun scanDao() : ScanDAO
}