package org.depinfo.clientmobileomnisus.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface OmnisusApi {
    @POST("api/id/signin")
    fun signIn(@Body utilisateur: SigninRequest): Call<String>

    @GET("api/grade")
    fun getGrade(): Call<UserDetailsResponse>

    @PUT("api/student")
    fun editUser(@Body publicName: String): Call<String>
}
