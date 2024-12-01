package org.depinfo.retrofit_demo.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtil {
    @JvmStatic
    fun get(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

        val service = retrofit.create<Service>(Service::class.java)
        return service
    }
}
