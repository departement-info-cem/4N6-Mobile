package com.example.composewaitrefresh.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WaitAndRefreshApi {
    @GET("exos/truc/complexe")
    fun listReposString(@Query("name") nom: String): Call<Truc>
}