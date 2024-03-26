package com.example.ecowise.model

data class Report(
    val id: String,
    val bulan: String,
    val tahun: String,
    val penghematan: Int,
    val total_usage: Double
)

