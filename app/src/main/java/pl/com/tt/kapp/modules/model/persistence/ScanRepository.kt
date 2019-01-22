package pl.com.tt.kapp.modules.model.persistence

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class ScanRepository (application: Application) {

    private var mScanDAO : ScanDAO
    private var mAllScans : LiveData<List<Scan>>

    init {
        val db = ScanDatabase.getInstance(application.applicationContext)
        mScanDAO = db!!.scanDao()
        mAllScans = mScanDAO.getAll()
    }

    fun getAllScans() = mAllScans

    fun insert(scan: Scan) {
        InsertAsyncTask(mScanDAO).execute(scan)
    }


    private class InsertAsyncTask(scanDAO: ScanDAO) : AsyncTask<Scan, Void, Boolean>() {
        private var mAsyncTaskDao : ScanDAO = scanDAO

        override fun doInBackground(vararg params: Scan): Boolean {
            mAsyncTaskDao.insertAll(params[0])
            return true
        }
    }
}