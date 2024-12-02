package org.depinfo.retrofit_demo.http

import org.depinfo.retrofit_demo.transfer.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @POST("api/id/signup")
    fun signup(@Body signupRequest: SignupRequest): Call<String>

    @GET("api/home")
    fun home(): Call<String>
}
