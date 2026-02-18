package org.depinfo.clientmobileomnisus.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .cookieJar(SessionCookieJar)
            .addInterceptor(logging)
            .build()
    }

    val api: OmnisusApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OmnisusApi::class.java)
    }
}
