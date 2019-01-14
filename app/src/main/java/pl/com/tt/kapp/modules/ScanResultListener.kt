package pl.com.tt.kapp.modules

interface ScanResultListener{
    fun onDiscoveryStarted()
    fun onDiscoveryFinished()
    fun onInterfaceEnabled()
    fun onInterfaceDisabled()
}