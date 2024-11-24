package org.depinfo.chictype.network.http

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("exos/chic/type/")
    fun getRandomNumber(): Call<Int?>
}
