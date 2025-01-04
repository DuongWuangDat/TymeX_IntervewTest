package com.example.tymex_currencyconverter_test.di

import com.example.tymex_currencyconverter_test.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
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
    val apiKey = BuildConfig.API_KEY
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
            .baseUrl("https://v6.exchangerate-api.com/v6/${apiKey}/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

}