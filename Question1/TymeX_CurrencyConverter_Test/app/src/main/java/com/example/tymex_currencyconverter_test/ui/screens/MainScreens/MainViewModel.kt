package com.example.tymex_currencyconverter_test.ui.screens.MainScreens

import android.provider.ContactsContract.Data
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymex_currencyconverter_test.data.model.DataRequest
import com.example.tymex_currencyconverter_test.data.repository.CurrencyInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round


@HiltViewModel
class MainViewModel @Inject constructor(
   private val currencyImpl: CurrencyInterface
) : ViewModel(){
    var isFromExpanded = mutableStateOf(false)
    var isToExpanded = mutableStateOf(false)
    var snackbarHostState = SnackbarHostState()
    var amountValue = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var listOfCode = mutableStateListOf<String>()
    var selectedBaseCode = mutableStateOf("")
    var selectedFromString = mutableStateOf("Select currency to convert from")
    var selectedToString = mutableStateOf("Select currency to convert to")
    var selectedTargetCode = mutableStateOf("")
    var conversionResult = mutableStateOf("0")
    var rateResult = mutableStateOf("0")
    var isFetchDataLoading = mutableStateOf(false)

    //Handle expand drop menu at from currency
    fun fromExpanded(){
        viewModelScope.launch {
            isFromExpanded.value=!isFromExpanded.value;
            isLoading.value = true
            currencyImpl.getCodes()
                .onSuccess {
                    listOfCode= it.supportedCode.map { res -> res.joinToString(" - ") }.toMutableStateList()
                    isLoading.value=false
                    isToExpanded.value=false
                }
                .onFailure {
                    println(it)
                    isLoading.value=false
                    isToExpanded.value=false
                    GlobalScope.launch {
                        snackbarHostState.showSnackbar("Something went wrong")
                    }
                }
        }
    }

    //Handle expand drop menu at to currency
    fun toExpanded(){
        viewModelScope.launch {
            isToExpanded.value =!isToExpanded.value
            isLoading.value = true
            currencyImpl.getCodes()
                .onSuccess {
                    listOfCode= it.supportedCode.map { res -> res.joinToString(" - ") }.toMutableStateList()
                    isLoading.value=false
                }
                .onFailure {
                    println(it)
                    isLoading.value=false
                    isToExpanded.value=false
                    GlobalScope.launch {
                        snackbarHostState.showSnackbar("Something went wrong")
                    }
                }
        }
    }

    //Handle fetch result of converter
    fun getData(){
        viewModelScope.launch {
            var dataRequest : DataRequest = DataRequest(
                amount = amountValue.value,
                baseCode = selectedBaseCode.value,
                targetCode = selectedTargetCode.value
            )
            isFetchDataLoading.value = true
            currencyImpl.getConverterResult(dataRequest)
                .onSuccess { e->
                    conversionResult.value = String.format("%.2f", e.conversionResult)
                    rateResult.value = e.conversionRate.toString()
                    isFetchDataLoading.value = false

                }
                .onFailure { e->
                    println(e)
                    isFetchDataLoading.value = false
                    GlobalScope.launch {
                        snackbarHostState.showSnackbar("Something went wrong")
                    }
                }
        }
    }

    //Handle select item in drop down menu (from)
    fun selectBaseCode(selectedString: String){
        viewModelScope.launch {
            var selectedList = selectedString.split(" - ")
            selectedBaseCode.value = selectedList.get(0);
            selectedFromString.value = selectedString
        }
    }

    //Handle select item in drop down menu (to)
    fun selectTargetCode(selectedString: String){
        viewModelScope.launch {
            var selectedList = selectedString.split(" - ")
            selectedTargetCode.value = selectedList.get(0);
            selectedToString.value = selectedString
        }
    }




}