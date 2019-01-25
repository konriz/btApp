package pl.com.tt.kapp.modules.model.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ResultDao {

    @Query("SELECT * FROM result")
    fun getAll(): LiveData<List<ResultDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(results : List<ResultDto>)

    @Delete
    fun delete(result : ResultDto)

    @Query("DELETE FROM result")
    fun deleteAll()

}