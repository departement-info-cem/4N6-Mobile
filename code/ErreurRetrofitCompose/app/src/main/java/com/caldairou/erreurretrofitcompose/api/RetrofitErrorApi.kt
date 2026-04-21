package com.caldairou.erreurretrofitcompose.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitErrorApi {
    @POST("exos/error/or/not/")
    fun erreurOuPas(@Body data: Repo): Call<String>
}