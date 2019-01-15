package pl.com.tt.kapp.modules.abstraction

interface Presentable {
    fun attachPresenter(presenter : ScanResultListener)
    fun detachPresenter()
}