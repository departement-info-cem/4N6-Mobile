package com.caldairou.erreurretrofitcompose.api

import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.InetAddress
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL = "https://fourn6-mobile-prof.onrender.com/"

    // Configuration du logging interceptor pour voir les requêtes/réponses HTTP
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // DNS personnalisé : tente la résolution normale, sinon retourne les IPs connues de Render
    private val customDns = object : Dns {
        override fun lookup(hostname: String): List<InetAddress> {
            return try {
                InetAddress.getAllByName(hostname).toList()
            } catch (e: Exception) {
                // Fallback : IPs de fourn6-mobile-prof.onrender.com (résolues via 8.8.8.8)
                listOf(
                    InetAddress.getByName("216.24.57.7"),
                    InetAddress.getByName("216.24.57.251")
                )
            }
        }
    }

    // Client OkHttp avec logging
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .dns(customDns)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: RetrofitErrorApi = retrofit.create(RetrofitErrorApi::class.java)
}
