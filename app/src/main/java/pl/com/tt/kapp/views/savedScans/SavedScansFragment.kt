package pl.com.tt.kapp.views.savedScans

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.saved_scans_fragment.*
import pl.com.tt.kapp.R

class SavedScansFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.saved_scans_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedPager.adapter = SavedScansPagerAdapter(activity!!.supportFragmentManager)
    }
}