package org.depinfo.chictype.network.http;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("exos/chic/type/")
    Call<Integer> getRandomNumber();
}
