package com.example.ecowise.model

data class Device(
    val id: String,
    val type: String,
    val lokasi: String,
    val icon: String,
    val merek: String,
    val duration: String,
    val daya: Int
)
//{
//    fun getDeviceHashMap(): HashMap<String, Any> {
//        return hashMapOf(
//            "id" to id,
//            "type" to type,
//            "lokasi" to lokasi,
//            "icon" to icon,
//            "merek" to merek,
//            "duration" to duration,
//            "daya" to daya
//        )
//    }
//}
