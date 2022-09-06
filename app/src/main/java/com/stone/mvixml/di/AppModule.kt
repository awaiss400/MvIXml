package com.stone.mvixml.di

import com.stone.mvixml.APIServicesss.ApiService
import com.stone.mvixml.APIServicesss.ApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideapi(retrofit: Retrofit.Builder): ApiService {
        return retrofit.build().create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideretro(): Retrofit.Builder{
        val logging= HttpLoggingInterceptor()
        logging.level= HttpLoggingInterceptor.Level.BODY
        val client= OkHttpClient.Builder()
        client.addInterceptor(logging)
        return Retrofit.Builder().baseUrl(BASE_URL).client(client.build()) .addConverterFactory(
            GsonConverterFactory.create())
    }}
