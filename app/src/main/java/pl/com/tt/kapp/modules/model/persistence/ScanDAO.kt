package pl.com.tt.kapp.modules.model.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ScanDAO {

    @Query("SELECT * FROM scan")
    fun getAll(): LiveData<List<Scan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg scans: Scan)

    @Delete
    fun delete(scan : Scan)

    @Query("DELETE FROM scan")
    fun deleteAll()

}