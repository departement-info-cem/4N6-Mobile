package ca.cem.composeretrofitbase.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Instance singleton de Retrofit configurée pour l'API KickMyB
 * Utilise un client OkHttp avec gestion des cookies pour maintenir la session
 */
object RetrofitInstance {
    private const val BASE_URL = "https://kickmyb-server.onrender.com/"

    // Client HTTP avec gestion des cookies de session
    private val okHttpClient = OkHttpClient.Builder()
        .cookieJar(SessionCookieJar)
        .build()

    // Instance Retrofit avec convertisseurs pour String et JSON
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Interface API prête à l'emploi
    val api: KickMyBApi = retrofit.create(KickMyBApi::class.java)
}

