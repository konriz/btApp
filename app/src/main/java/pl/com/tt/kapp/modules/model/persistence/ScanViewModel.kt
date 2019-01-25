package pl.com.tt.kapp.modules.model.persistence

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class ScanViewModel(application: Application) : AndroidViewModel(application) {

    private val mScanRepository = ScanRepository(application)
    private val mBluetoothScans = mScanRepository.getBluetoothScans()
    private val mWifiScans = mScanRepository.getWifiScans()

    fun insert(scan : Scan, results: List<ResultDto>){
        mScanRepository.insert(scan, results)
    }

    fun bluetoothScans() = mBluetoothScans

    fun wifiScans() = mWifiScans

    fun deleteAll() = mScanRepository.deleteAll()

}