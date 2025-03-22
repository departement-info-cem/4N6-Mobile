package org.sabourin.swiperefresh

import retrofit2.Call
import retrofit2.http.GET


interface Service {
    // https://4n6.azurewebsites.net/exos/truc/list
    @GET("exos/truc/list")
    fun vaChercherLaListe(): Call<List<Truc?>>
}
