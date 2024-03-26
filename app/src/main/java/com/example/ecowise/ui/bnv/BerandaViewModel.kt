package com.example.ecowise.ui.bnv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecowise.model.Device
import com.google.firebase.firestore.FirebaseFirestore

class BerandaViewModel : ViewModel() {
    private val _listDevice = MutableLiveData<ArrayList<Device>>()

    val listDevice: LiveData<ArrayList<Device>>
        get() = _listDevice

    init{
        _listDevice.value = ArrayList()
        prepareResource()
    }

    private fun prepareResource(){
        val db = FirebaseFirestore.getInstance()
        val deviceRef = db.collection("device")

        deviceRef.get().addOnCompleteListener {task ->
            if(task.isSuccessful){
                val listItem = arrayListOf<Device>()
                for(document in task.result!!.take(4)){
                    val deviceData = document.data!!
                    val device = Device(
                        id = document.id,
                        type = deviceData["type"] as String,
                        lokasi = deviceData["lokasi"] as String,
                        icon = deviceData["icon"] as String,
                        merek = deviceData["merek"] as String,
                        duration = deviceData["duration"] as String,
                        daya = (deviceData["daya"] as Long).toInt()
                    )
                    listItem.add(device)
                }
                _listDevice.value = listItem

            }

        }
    }
}