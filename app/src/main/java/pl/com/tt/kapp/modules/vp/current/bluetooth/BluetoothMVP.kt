package pl.com.tt.kapp.modules.vp.current.bluetooth

import android.support.v4.app.Fragment
import pl.com.tt.kapp.modules.abstraction.ScanDTO

class BluetoothMVP{

    interface Presenter{
        fun setBtSwitch()
        fun onBluetoothSwitch(state : Boolean)
        fun onScanButtonPressed()
        fun onSaveButtonPressed(fragment : Fragment)
        fun onDestroy()
    }

    interface View{
        fun showLoader()
        fun hideLoader()
        fun setSwitch(state : Boolean)
        fun showToast(message : Int, length : Int)
        fun updateData(results : ScanDTO)
    }

}
