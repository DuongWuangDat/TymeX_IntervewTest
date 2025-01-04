package com.example.tymex_currencyconverter_test.data.repository

import com.example.tymex_currencyconverter_test.data.model.CodeResponse
import com.example.tymex_currencyconverter_test.data.model.DataRequest
import com.example.tymex_currencyconverter_test.data.model.DataResponse
import com.example.tymex_currencyconverter_test.data.service.ApiService
import retrofit2.Retrofit
import javax.inject.Inject

interface CurrencyInterface {

    suspend fun getConverterResult(dataRequest: DataRequest) : Result<DataResponse>

    suspend fun getCodes(): Result<CodeResponse>
}

class CurrencyRepository @Inject constructor(
    apiClient: Retrofit
) : CurrencyInterface{
    private val service = apiClient.create(ApiService::class.java)
    override suspend fun getConverterResult(dataRequest: DataRequest): Result<DataResponse> {
        return try{
            val res = service.getCurrencyConverter(dataRequest.baseCode,dataRequest.targetCode, dataRequest.amount)
            Result.success(res)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun getCodes(): Result<CodeResponse> {
        return try{
            val res = service.getCodes()
            Result.success(res)
        }catch(e:Exception){
            Result.failure(e)
        }
    }


}