package org.depinfo.retrofit_demo.http

import org.depinfo.retrofit_demo.transfer.Repo
import org.depinfo.retrofit_demo.transfer.Utilisateur
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("users/{utilisateur}/repos")
    fun listRepos(@Path("utilisateur") utilisateur: String): Call<List<Repo>>

    @GET("users/{utilisateur}")
    fun utilisateur(@Path("utilisateur") utilisateur: String): Call<Utilisateur>
}
