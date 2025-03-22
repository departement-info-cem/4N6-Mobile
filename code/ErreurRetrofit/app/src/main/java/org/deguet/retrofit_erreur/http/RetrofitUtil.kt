package org.deguet.retrofit_erreur.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    @JvmStatic
    fun get(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fourn6-mobile-prof.onrender.com/")
            .build()

        val service = retrofit.create<Service>(Service::class.java)
        return service
    }
}
