package pl.com.tt.kapp.modules.wifi.presenter

import pl.com.tt.kapp.modules.wifi.WifiMVP

class WifiPresenter(var view : WifiMVP.View?) : WifiMVP.Presenter {

    override fun setWifiSwitch() {
        view?.setSwitch(true)
    }

    fun enableWifi(){

    }

    fun disableWifi(){

    }

    fun scanNetworks(){

    }


    fun onDestroy(){
        view = null
    }

}