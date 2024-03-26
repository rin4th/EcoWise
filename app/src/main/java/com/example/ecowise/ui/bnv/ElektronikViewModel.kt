package com.example.ecowise.ui.bnv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecowise.model.Device
import com.example.ecowise.model.Report
import com.google.firebase.firestore.FirebaseFirestore

class ElektronikViewModel : ViewModel() {

    private val _listReport = MutableLiveData<ArrayList<Report>>()
    private val _listDevice = MutableLiveData<ArrayList<Device>>()

    val listReport: LiveData<ArrayList<Report>>
        get() = _listReport

    val listDevice: LiveData<ArrayList<Device>>
        get() = _listDevice

    init{
        _listReport.value = ArrayList()
        _listDevice.value = ArrayList()
        prepareResource()
    }

    private fun prepareResource(){
        val db = FirebaseFirestore.getInstance()
        val reportRef = db.collection("report_usage")
        val deviceRef = db.collection("device")

        reportRef.get().addOnCompleteListener {task ->
            if(task.isSuccessful){
                val listItem = arrayListOf<Report>()
                for(document in task.result!!.take(4)){
                    val reportData = document.data!!
                    val report = Report(
                        id = document.id,
                        bulan = reportData["bulan"] as String,
                        tahun = reportData["tahun"] as String,
                        penghematan = (reportData["penghematan"] as Long).toInt(),
                        total_usage = (reportData["total_usage"] as Double).toDouble()                    )
                    listItem.add(report)
                }
                _listReport.value = listItem

            }

        }

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