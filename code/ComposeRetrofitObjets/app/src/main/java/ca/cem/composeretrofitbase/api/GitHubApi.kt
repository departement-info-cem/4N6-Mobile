package ca.cem.composeretrofitbase.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{utilisateur}/repos")
    fun listRepos(@Path("utilisateur") utilisateur: String): Call<List<Repo>>
}
