package com.example.tymex_currencyconverter_test.data.model

data class DataRequest (
    val baseCode : String,
    val targetCode: String,
    val amount: String
)