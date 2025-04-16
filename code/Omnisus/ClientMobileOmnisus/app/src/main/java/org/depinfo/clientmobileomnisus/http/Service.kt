package org.depinfo.clientmobileomnisus.http

import org.depinfo.clientmobileomnisus.http.dto.SigninRequest
import org.depinfo.clientmobileomnisus.http.dto.UserDetailsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface Service {
    @POST("api/id/signin")
    fun signIn(@Body utilisateur: SigninRequest): Call<String>

    @GET("api/grade")
    fun getGrade(): Call<UserDetailsResponse>

    @PUT("api/student")
    fun editUser(@Body publicName: String): Call<String>
}