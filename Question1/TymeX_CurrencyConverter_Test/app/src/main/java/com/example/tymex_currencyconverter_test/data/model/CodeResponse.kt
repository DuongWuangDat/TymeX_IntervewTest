package com.example.tymex_currencyconverter_test.data.model

import com.google.gson.annotations.SerializedName

data class CodeResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("supported_codes")
    val supportedCode: List<List<String>>
)