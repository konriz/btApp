package pl.com.tt.kapp.modules.model.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ScanResultJoinDao {

    @Insert
    fun insert(scanResultJoin: ScanResultJoin)

    @Query("DELETE FROM scan")
    fun deleteAll()

    @Query("SELECT * FROM scan INNER JOIN scan_result_join ON scan.date=scan_result_join.scan_date WHERE scan_result_join.result_address=:address")
    fun scansForResult(address : String) : LiveData<List<Scan>>

    @Query("SELECT * FROM result INNER JOIN scan_result_join ON result.address=scan_result_join.result_address WHERE scan_result_join.scan_date=:date")
    fun resultsForScan(date : String) : LiveData<List<ResultDto>>

}