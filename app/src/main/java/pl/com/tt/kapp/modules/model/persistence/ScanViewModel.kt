package pl.com.tt.kapp.modules.model.persistence

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class ScanViewModel(application: Application) : AndroidViewModel(application) {

    private val mScanRepository = ScanRepository(application)
    private val mAllScans = mScanRepository.getAllScans()

    fun insert(scan: Scan) = mScanRepository.insert(scan)

    fun allScans() = mAllScans

}