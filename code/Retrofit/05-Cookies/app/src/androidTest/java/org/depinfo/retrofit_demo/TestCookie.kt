package org.depinfo.retrofit_demo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.depinfo.retrofit_demo.http.UtilitaireRetrofit
import org.depinfo.retrofit_demo.transfer.SignupRequest
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TestCookie {

    // Passe une fois mais pas 2
    // Peut ne pas passer parce que le serveur n'est pas parti
    @Test
    fun testSignup() {
        // service > retrofit > client okhttp > cookie Jar
        val service = UtilitaireRetrofit.get()
        val request = SignupRequest()
        request.username = "joris@pipo.org"
        request.password = "123456"
        val call: Call<String> = service.signup(request)
        val response = call.execute()
        val resultat = response.body()
        assert(resultat!!.contains("joris@pipo.org"))
    }
}
