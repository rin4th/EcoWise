package com.example.ecowise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecowise.R
import com.example.ecowise.model.Device

class DeviceAdapter(private var devices: List<Device>, private val onItemClick: (Device) -> Unit) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_device, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ViewHolder, position: Int) {
        holder.bind(devices[position])
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClick(devices[position].id)
        }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var icon: ImageView = itemView.findViewById(R.id.iv_device_card)
        private var type: TextView = itemView.findViewById(R.id.tv_device_card)
        private var daya: TextView = itemView.findViewById(R.id.tv_device_status)
        private var duration: TextView = itemView.findViewById(R.id.tv_device_hour)

        val daya_desc = "Konsumsi energy "

        fun bind(device: Device) {
            Glide.with(itemView.context)
                .load(device.icon)
                .into(icon)
            type.text = device.type
            daya.text = daya_desc.plus(device.daya.toString()).plus("kWh")
            duration.text = device.duration
        }

    }

    interface OnItemClickListener {
        fun onItemClick(deviceId: String)
    }

    fun updateCourses(newDevice: List<Device>) {
        val diffCallback = DeviceDiffCallback(devices, newDevice)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        devices = newDevice
        diffResult.dispatchUpdatesTo(this)
    }


}

class DeviceDiffCallback(
    private val oldList: List<Device>,
    private val newList: List<Device>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}