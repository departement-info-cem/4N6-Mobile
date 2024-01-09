package org.depinfo.chictype.network.http;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {
    public static Service get(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://4n6.azurewebsites.net/")
                .build();

        return retrofit.create(Service.class);
    }
}
