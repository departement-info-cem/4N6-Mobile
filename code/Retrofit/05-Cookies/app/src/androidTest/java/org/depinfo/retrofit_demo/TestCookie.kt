package org.depinfo.retrofit_demo

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.depinfo.retrofit_demo.http.UtilitaireRetrofit
import org.depinfo.retrofit_demo.transfer.SignupRequest
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call

@RunWith(AndroidJUnit4::class)
class TestCookie {

    // ouvrir https://kickmyb-server.onrender.com/ dans un navigateur
    // pour réveiller le serveur

    // Passe une fois mais pas 2
    // Peut ne pas passer parce que le serveur n'est pas parti
    @Test
    fun testSignup() {
        // service > retrofit > client okhttp > cookie Jar
        val service = UtilitaireRetrofit.get()
        val request = SignupRequest()
        request.username = "raoul@pipo.org"
        request.password = "123456"
        val call: Call<String> = service.signup(request)
        val response = call.execute()
        val resultat = response.body()
        assert(resultat!!.contains("raoul@pipo.org"))
    }

    // Lancer ce test seulement
    @Test
    fun testSignupPuisHome() {
        val service = UtilitaireRetrofit.get()
        val request = SignupRequest()
        var random = (0..1000).random()
        request.username = "joris"+random.toString()+"@pipo.org"
        request.password = "123456"
        val call: Call<String> = service.signup(request)
        val response = call.execute()
        val resultat = response.body()
        println("Retour du signup : " + resultat)
        val callHome: Call<String> = service.home()
        val responseHome = callHome.execute()
        val resultatHome = responseHome.body()
        println("Retour du home : " + resultatHome)
        assert(resultatHome != null)
    }

    // Lancer ce test seulement
    @Test
    fun testHomeSansSignup() {
        val service = UtilitaireRetrofit.get()
        val callHome: Call<String> = service.home()
        val responseHome = callHome.execute()
        val resultatHome = responseHome.body()
        assert(resultatHome != null)
    }
}
