package com.example.dundermifflinapp.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(3, TimeUnit.MINUTES)
            writeTimeout(3, TimeUnit.MINUTES)
            readTimeout(3, TimeUnit.MINUTES)

            addInterceptor { chain ->
                val original = chain.request()

                val requesBuilder = original.newBuilder()
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept-Language", Locale.getDefault().language)


                val request = requesBuilder.build()
                chain.proceed(request)
            }
        }

        val url = "https://0r4v1a7b28.execute-api.sa-east-1.amazonaws.com/Stage/"

        Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}