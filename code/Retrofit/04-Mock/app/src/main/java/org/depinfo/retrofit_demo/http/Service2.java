package org.depinfo.retrofit_demo.http;

import org.depinfo.retrofit_demo.transfer.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service2 {

    @POST("create")
    Call<Utilisateur> creer(Utilisateur utilisateur);

    @GET("utilisateurs")
    Call<List<Utilisateur>> utilisateurs();

}