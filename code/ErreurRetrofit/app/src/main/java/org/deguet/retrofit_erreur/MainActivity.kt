package org.deguet.retrofit_erreur

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.deguet.retrofit_erreur.databinding.ActivityMainBinding
import org.deguet.retrofit_erreur.http.RetrofitUtil.get
import org.deguet.retrofit_erreur.transfer.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val service = get()
        binding.btn.setOnClickListener{
            val nom = binding.et.getText().toString()
            val repo = Repo()
            repo.nom = nom
            service.erreurOuPas(repo).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response!!.isSuccessful()) {
                        binding.tv.setText(response.body())
                    } else {
                        // ERROR ERROR ERROR
                        try {
                            val corpsErreur = response.errorBody()!!.string()
                            Log.i("RETROFIT", "le code " + response.code())
                            Log.i("RETROFIT", "le message " + response.message())
                            Log.i("RETROFIT", "le corps " + corpsErreur)
                            Log.i(
                                "RETROFIT",
                                "le corps encore " + response.errorBody()!!.string()
                            )
                            if (corpsErreur.contains("TropCourt")) {
                                // TODO remplacer par un objet graphique mieux qu'un toast
                                Toast.makeText(
                                    this@MainActivity,
                                    "Ce message est trop court",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // TODO ici aussi il va falloir avoir un message d'erreur
                    val a = 0
                }
            })
        }
    }
}
