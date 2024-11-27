package org.deguet.retrofit_erreur.http

import org.deguet.retrofit_erreur.transfer.Repo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {
    @POST("exos/error/or/not/")
    fun erreurOuPas(@Body data: Repo): Call<String>
}
