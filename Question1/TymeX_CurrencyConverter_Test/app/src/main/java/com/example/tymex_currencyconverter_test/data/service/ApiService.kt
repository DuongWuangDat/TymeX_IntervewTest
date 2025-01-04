package com.example.tymex_currencyconverter_test.data.service

import android.icu.util.CurrencyAmount
import com.example.tymex_currencyconverter_test.data.model.CodeResponse
import com.example.tymex_currencyconverter_test.data.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path


interface ApiService {

    @GET("pair/{baseCode}/{targetCode}/{amount}")
    suspend fun getCurrencyConverter(
        @Path("baseCode") baseCode : String,
        @Path("targetCode") targetCode: String,
        @Path("amount") amount: String
    ) : DataResponse

    @GET("codes")
    suspend fun getCodes() : CodeResponse

}