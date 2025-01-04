package com.example.tymex_currencyconverter_test.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGSON(): Gson{
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient() : OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/c7db9735f221cc0f0ef45ab7/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

}