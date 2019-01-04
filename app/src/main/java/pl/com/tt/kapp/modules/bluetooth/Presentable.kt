package pl.com.tt.kapp.modules.bluetooth

interface Presentable {

    fun attachPresenter(presenter : BluetoothMVP.ScanResultListener)
    fun detachPresenter()
}