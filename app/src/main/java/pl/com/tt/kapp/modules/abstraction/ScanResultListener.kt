package pl.com.tt.kapp.modules.abstraction

interface ScanResultListener{
    fun onDiscoveryStarted()
    fun onDiscoveryFinished()
    fun onInterfaceEnabled()
    fun onInterfaceDisabled()
}