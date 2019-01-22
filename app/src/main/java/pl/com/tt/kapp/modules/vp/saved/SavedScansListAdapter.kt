package pl.com.tt.kapp.modules.vp.saved

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.scan_row.view.*
import pl.com.tt.kapp.R
import pl.com.tt.kapp.modules.model.persistence.Scan

class SavedScansListAdapter(private var scans : List<Scan>)
    : RecyclerView.Adapter<SavedScansListAdapter.ScansListViewHolder>() {

    class ScansListViewHolder(val scanRowView : View) : RecyclerView.ViewHolder(scanRowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ScansListViewHolder {
        val scanRowView = LayoutInflater.from(p0.context).inflate(R.layout.scan_row, p0, false) as View
        return ScansListViewHolder(scanRowView)
    }

    override fun onBindViewHolder(p0: ScansListViewHolder, p1: Int) {
        val scan = scans[p1]
        val row = p0.scanRowView

        row.date.text = scan.date
        row.count.text = scan.devicesCount.toString()
    }

    override fun getItemCount() = scans.size

    fun update(scans : List<Scan>){
        this.scans = scans
        notifyDataSetChanged()
    }
}