package org.deguet.progress

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.deguet.progress.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var progressD: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startDownload()
                RetrofitUtil.get().waitAMinute().enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        endDownload()
                        Toast.makeText(getApplicationContext(), "Yes bouton", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        endDownload()
                        Toast.makeText(getApplicationContext(), "Ouch bouton", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        })

        // On affiche le dialogue avant de lancer la requete
        progressD = ProgressDialog.show(
            this@MainActivity, "Please wait",
            "Long operation starts...", true
        )
        RetrofitUtil.get().waitAMinute().enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>?, response: Response<String?>?) {
                // si on recoit une reponse du serveur, premier truc : on ferme le dialogue
                progressD!!.dismiss()
                Toast.makeText(getApplicationContext(), "Yes chargement", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                // attention on ferme aussi le dialogue sur erreur, sinon il ne part jamais
                progressD!!.dismiss()
                Toast.makeText(getApplicationContext(), "Ouch chargement", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun startDownload() {
        binding.progress.visibility = View.VISIBLE
        binding.stuff.visibility = View.GONE
    }

    private fun endDownload() {
        binding.progress.visibility = View.GONE //INVISIBLE occupe de l'espace GONE non
        binding.stuff.visibility = View.VISIBLE
    }
}
