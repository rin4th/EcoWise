package com.example.ecowise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecowise.R
import com.example.ecowise.model.Device
import com.google.android.material.materialswitch.MaterialSwitch

class DeviceAdapter : ListAdapter<Device, DeviceAdapter.ViewHolder>(DIFFCALLBACK){

    private lateinit var onItemClickCallback: OnItemClickListener

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var icon: ImageView = itemView.findViewById(R.id.iv_device_card)
        private var type: TextView = itemView.findViewById(R.id.tv_device_card)
        private var daya: TextView = itemView.findViewById(R.id.tv_device_status)
        private var duration: TextView = itemView.findViewById(R.id.tv_device_hour)
        private var switch: MaterialSwitch = itemView.findViewById(R.id.ms_device)

        val daya_desc = "Konsumsi energy "

        fun bind(device: Device, onItemCallback: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(device.icon)
                .into(icon)
            //icon.setImageResource(R.drawable.ac)
            type.text = device.type
            daya.text = daya_desc.plus(device.daya.toString()).plus("kWh")
            duration.text = device.duration
            switch.setOnClickListener { onItemCallback.onItemClick() }
        }

    }

    interface OnItemClickListener {
        fun onItemClick()
    }



    companion object {
        val DIFFCALLBACK = object : DiffUtil.ItemCallback<Device>() {
            override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_device, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickCallback)
    }

}
