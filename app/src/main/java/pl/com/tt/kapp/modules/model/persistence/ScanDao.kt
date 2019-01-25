package pl.com.tt.kapp.modules.model.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ScanDao {

    @Query("SELECT * FROM scan")
    fun getAll(): LiveData<List<Scan>>

    @Query("SELECT * FROM scan WHERE scan.type = 0")
    fun getBluetooth() : LiveData<List<Scan>>

    @Query("SELECT * FROM scan WHERE scan.type = 1")
    fun getWifi() : LiveData<List<Scan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg scans: Scan)

    @Delete
    fun delete(scan : Scan)

    @Query("DELETE FROM scan")
    fun deleteAll()

}