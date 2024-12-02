package org.depinfo.retrofit_demo.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun get(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()
        val service = retrofit.create<Service>(Service::class.java)
        return service
    }
}
