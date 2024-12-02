package org.depinfo.retrofit_demo.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object UtilitaireRetrofit {
    private var instance: Service? = null

    fun get(): Service {
        if (instance == null) { //  ca sera le cas au tout premier appel
            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .baseUrl("https://kickmyb-server.onrender.com/")
                .build()
            instance = retrofit.create<Service?>(Service::class.java)
            return instance!!
        } else {
            return instance!!
        }
    }

    private fun client(): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(SessionCookieJar)
            .build()
    }
}
