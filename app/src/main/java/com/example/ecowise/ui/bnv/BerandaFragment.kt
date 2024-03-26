package com.example.ecowise.ui.bnv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.adapter.DeviceAdapter
import com.example.ecowise.model.Device
import com.google.firebase.firestore.FirebaseFirestore


class BerandaFragment : Fragment() {

    private lateinit var rv: RecyclerView

    private var devices: MutableList<Device> = mutableListOf()

    private lateinit var deviceAdapter: DeviceAdapter
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_device_beranda)

        prepareResource()
    }

    private fun prepareResource(){
        val db = FirebaseFirestore.getInstance()
        val deviceRef = db.collection("device")

        deviceRef.get().addOnCompleteListener {task ->
            if(task.isSuccessful){
                devices.clear()
                for(document in task.result!!){
                    val deviceData = document.data!!
                    val device = Device(
                        id = document.id,
                        type = deviceData["type"] as String,
                        lokasi = deviceData["lokasi"] as String,
                        icon = deviceData["icon"] as String,
                        merek = deviceData["merek"] as String,
                        duration = deviceData["duration"] as String,
                        daya = deviceData["daya"] as Int
                    )
                    devices.add(device)
                }
                prepareRecyclerView()
                prepareAdapter()
            }

        }
    }

    private fun prepareRecyclerView(){
        rv.layoutManager = GridLayoutManager(context, 2)
        rv.setHasFixedSize(true)
    }

    private fun prepareAdapter(){
        if (::deviceAdapter.isInitialized){
            deviceAdapter.updateCourses(devices)
        } else {

        }
    }
}