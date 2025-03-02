package org.depinfo.chictype.network.http

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtil {
    fun get(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://fourn6-mobile-prof.onrender.com/")
            .build()

        return retrofit.create<Service>(Service::class.java)
    }
}
