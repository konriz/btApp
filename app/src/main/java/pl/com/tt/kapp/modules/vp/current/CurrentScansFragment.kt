package pl.com.tt.kapp.modules.vp.current

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.current_scans_fragment.*
import pl.com.tt.kapp.R

class CurrentScansFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.current_scans_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentPager.adapter =
                CurrentScansPagerAdapter(activity!!.supportFragmentManager)
    }
}
