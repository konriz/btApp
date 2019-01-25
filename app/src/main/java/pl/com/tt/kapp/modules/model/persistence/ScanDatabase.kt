package pl.com.tt.kapp.modules.model.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Scan::class, ResultDto::class, ScanResultJoin::class], version = 1, exportSchema = false)
abstract class ScanDatabase : RoomDatabase(){
    abstract fun scanDao() : ScanDao
    abstract fun resultDao() : ResultDao
    abstract fun scanResultJoinDao() : ScanResultJoinDao

    companion object {
        private var INSTANCE: ScanDatabase? = null

        fun getInstance(context : Context): ScanDatabase? {
            if (INSTANCE == null){
                synchronized(ScanDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ScanDatabase::class.java,
                        "scan_database.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}