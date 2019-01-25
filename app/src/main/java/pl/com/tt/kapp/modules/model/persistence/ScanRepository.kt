package pl.com.tt.kapp.modules.model.persistence

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log

private const val TAG = "Scan Repository"

class ScanRepository (application: Application) {

    private var mScanDao : ScanDao
    private var mResultDao : ResultDao
    private var mScanResultDao : ScanResultJoinDao
    private var mBluetoothScans : LiveData<List<Scan>>
    private var mWifiScans : LiveData<List<Scan>>

    init {
        val db = ScanDatabase.getInstance(application.applicationContext)
        mScanDao = db!!.scanDao()
        mResultDao = db.resultDao()
        mScanResultDao = db.scanResultJoinDao()

        mBluetoothScans = mScanDao.getBluetooth()
        mWifiScans = mScanDao.getWifi()
    }

    fun getBluetoothScans() = mBluetoothScans

    fun getWifiScans() = mWifiScans

    fun insert(scan: Scan, results: List<ResultDto>) {
        Log.i(TAG, "Inserting scan")
        InsertAsyncTask(mScanDao, mResultDao, mScanResultDao).execute(AsyncParams(scan, results))
    }

    fun deleteAll() {
        Log.i(TAG, "Deleting all scans")
        DeleteAsyncTask(mScanDao, mResultDao, mScanResultDao).execute()
    }

    private class AsyncParams(var scan: Scan, var results: List<ResultDto>)


    private class InsertAsyncTask(scanDao: ScanDao, resultDao: ResultDao, scanResultJoinDao: ScanResultJoinDao) : AsyncTask<AsyncParams, Void, Boolean>() {
        private var mAsyncScanDao : ScanDao = scanDao
        private var mAsyncResultDao : ResultDao = resultDao
        private var mAsyncScanResultJoinDao : ScanResultJoinDao = scanResultJoinDao

        override fun doInBackground(vararg params : AsyncParams): Boolean {
            val param = params[0]
            mAsyncScanDao.insertAll(param.scan)
            mAsyncResultDao.insertAll(param.results)
            for(result in param.results){
                mAsyncScanResultJoinDao.insert(ScanResultJoin(param.scan.date, result.address))
            }

            return true
        }
    }

    private class DeleteAsyncTask(scanDao: ScanDao, resultDao: ResultDao, scanResultJoinDao: ScanResultJoinDao) : AsyncTask<Void, Void, Boolean>() {
        private var mAsyncScanDao : ScanDao = scanDao
        private var mAsyncResultDao : ResultDao = resultDao
        private var mAsyncScanResultJoinDao : ScanResultJoinDao = scanResultJoinDao

        override fun doInBackground(vararg params: Void?): Boolean {
            mAsyncScanResultJoinDao.deleteAll()
            mAsyncResultDao.deleteAll()
            mAsyncScanDao.deleteAll()
            return true
        }
    }
}