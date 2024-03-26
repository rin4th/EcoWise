package com.example.ecowise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.model.Report
import com.google.android.material.materialswitch.MaterialSwitch

class ReportAdapter : ListAdapter<Report, ReportAdapter.ViewHolder>(
    ReportAdapter.DIFFCALLBACK
) {

    private lateinit var onItemClickCallback: OnItemClickListener

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var date: TextView = itemView.findViewById(R.id.tv_energy)
        private var total_energy: TextView = itemView.findViewById(R.id.tv_energy_value)
        private var kenaikan: TextView = itemView.findViewById(R.id.tv_stats_energy)
        private var desc: TextView = itemView.findViewById(R.id.tv_desc_stat_2)

        val daya_desc = "Kamu telah menghemat "
        val daya_desc2 = " penggunaan listrik, lanjutkan penghematan dan kumpulkan reward "

        fun bind(report: Report, onItemCallback: OnItemClickListener) {

            date.text = report.bulan.plus(" ").plus(report.tahun)
            total_energy.text = report.total_usage.toString()
            kenaikan.text = report.penghematan.toString().plus("").plus("%")
            desc.text = daya_desc.plus(report.penghematan).plus(report.penghematan.toString())
                .plus(daya_desc2)
        }

    }

    interface OnItemClickListener {
        fun onItemClick()
    }


    companion object {
        val DIFFCALLBACK = object : DiffUtil.ItemCallback<Report>() {
            override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ReportAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_energy, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReportAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickCallback)
    }
}