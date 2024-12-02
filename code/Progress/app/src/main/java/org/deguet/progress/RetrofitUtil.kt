package org.deguet.progress

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtil {
    fun get(): ServiceAttente {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://fourn6-mobile-prof.onrender.com/")
            .build()
        val service = retrofit.create<ServiceAttente>(ServiceAttente::class.java)
        return service
    }
}
