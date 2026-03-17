package ca.cem.composeretrofitbase.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("exam/2026/personnages/{LowLimit}/{HighLimit}")
    fun listRepos(@Path("LowLimit") lowLimit: Double, @Path("HighLimit") highLimit : Double): Call<List<Repo>>
}
