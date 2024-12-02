package org.depinfo.retrofit_demo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.depinfo.retrofit_demo.http.UtilitaireRetrofit
import org.depinfo.retrofit_demo.transfer.SignupRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // appeler un service de liste et afficher dans le log
        val service = UtilitaireRetrofit.get()
        var request = SignupRequest()
        request.username = "joris@pipo.org"
        request.password = "123456"
        service.signup(request).enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response!!.isSuccessful()) {
                    // http 200 http tout s'est bien passé
                    val resultat : String? = response.body()
                    Log.i("RETROFIT", resultat!!.length.toString() + "")
                } else {
                    // cas d'erreur http 400 404
                    Log.i("RETROFIT", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // erreur accès réseau ou alors GSON
                Log.i("RETROFIT", t!!.message!!)
            }
        })
    }
}