package pl.com.tt.kapp.modules.model.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ScanDAO {

    @Query("SELECT * FROM scan")
    fun getAll(): LiveData<List<Scan>>

    @Insert
    fun insertAll(vararg scans: Scan)

    @Delete
    fun delete(scan : Scan)

}