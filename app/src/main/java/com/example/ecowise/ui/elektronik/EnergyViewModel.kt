package com.example.ecowise.ui.elektronik

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecowise.model.Report
import com.google.firebase.firestore.FirebaseFirestore

class EnergyViewModel : ViewModel() {
    private var _listReport = MutableLiveData<ArrayList<Report>>()

    val listReport: MutableLiveData<ArrayList<Report>>
        get() = _listReport

    init {
        _listReport.value = ArrayList()
        prepareResource()
    }

    private fun prepareResource() {
        val db = FirebaseFirestore.getInstance()
        val reportRef = db.collection("report_usage")

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
    }
}