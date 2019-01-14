package pl.com.tt.kapp.modules

interface Presentable {
    fun attachPresenter(presenter : ScanResultListener)
    fun detachPresenter()
}