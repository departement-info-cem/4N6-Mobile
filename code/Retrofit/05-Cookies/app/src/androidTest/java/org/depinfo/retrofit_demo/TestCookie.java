package org.depinfo.retrofit_demo;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.depinfo.retrofit_demo.http.RetrofitCookie;
import org.depinfo.retrofit_demo.http.ServiceCookie;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class TestCookie {
    @Test
    public void testSimple() throws IOException {
        // service > retrofit > client okhttp > cookie Jar
        for (int i = 0; i < 10; i++) {
            ServiceCookie service = RetrofitCookie.get();
            Call<String> call = service.cookieEcho();
            Response<String> response = call.execute();
            String resultat = response.body();
            Log.i("RETROFIT", resultat);
        }
    }
}
