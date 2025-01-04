package com.example.tymex_currencyconverter_test.di

import com.example.tymex_currencyconverter_test.data.repository.CurrencyInterface
import com.example.tymex_currencyconverter_test.data.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryBinding {

    @Binds
    fun bindCurrencyImpl(currencyImpl: CurrencyRepository) : CurrencyInterface
}