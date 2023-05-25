package com.example.lab72.retrofit

data class BitcoinPrice(
    val time: TimeData,
    val disclaimer: String,
    val name: String,
    val bpi: Map<String, CurrencyData>
)
data class TimeData(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)
data class CurrencyData(
    val code: String,
    val symbol: String,
    val rate: String,
    val description: String,
    val rate_float: Float
)
