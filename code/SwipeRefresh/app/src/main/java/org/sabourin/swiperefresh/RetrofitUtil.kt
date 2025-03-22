package org.sabourin.swiperefresh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun get(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fourn6-mobile-prof.onrender.com/")
            .build()

        val service = retrofit.create(
            Service::class.java
        )
        return service
    }
}