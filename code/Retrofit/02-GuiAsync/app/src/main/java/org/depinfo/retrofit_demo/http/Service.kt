package org.depinfo.retrofit_demo.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {
    @GET("users/{utilisateur}/repos")
    fun listReposString(@Path("utilisateur") utilisateur: String): Call<String>
}
