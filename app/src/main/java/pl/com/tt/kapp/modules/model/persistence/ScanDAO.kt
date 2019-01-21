package pl.com.tt.kapp.modules.model.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScanDAO {

    @Query("SELECT * FROM scan")
    fun getAll(): List<Scan>

    @Insert
    fun insertAll(vararg scans: Scan)

    @Delete
    fun delete(scan : Scan)
}