package org.depinfo.retrofit_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.depinfo.retrofit_demo.databinding.ActivityMainBinding
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.depinfo.retrofit_demo.http.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        // appeler un service de liste et afficher dans le textview
        binding.btn.setOnClickListener({
            val service: Service = RetrofitUtil.get()
            val nom: String = binding.et.text.toString()
            val call: Call<String> = service.listReposString(nom)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        // http 200 http tout s'est bien passé
                        val resultat = response.body()
                        binding.tv.text = resultat
                    } else {
                        // cas d'erreur http 400 404 etc.
                        binding.tv.text = "REPONSE ERREUR : " + response.code()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // erreur accès réseau ou alors GSON
                    binding.tv.text = "PAS DE REPONSE : " + t.message
                }
            })
        })
    }
}
