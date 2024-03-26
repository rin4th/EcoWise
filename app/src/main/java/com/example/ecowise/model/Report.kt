package com.example.ecowise.model

data class Report(
    val id: String,
    val bulan: String,
    val tahun: String,
    val penghematan: Int,
    val total_usage: Double
)
//{
//    fun getDeviceHashMap(): HashMap<String, Any> {
//        return hashMapOf(
//            "id" to id,
//            "bulan" to bulan,
//            "tahun" to tahun,
//            "penghematan" to penghematan,
//            "total_usage" to total_usage,
//        )
//    }
//}
