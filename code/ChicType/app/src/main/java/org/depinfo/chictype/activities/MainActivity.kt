package org.depinfo.chictype.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.depinfo.chictype.databinding.ActivityMainBinding
import org.depinfo.chictype.network.http.RetrofitUtil
import org.depinfo.chictype.network.http.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    private lateinit var service: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(getLayoutInflater())
        service = RetrofitUtil.get()

        setContentView(binding.getRoot())

        setupBtnRandom()
    }

    private fun setupBtnRandom() {
        binding!!.btnRandom.setOnClickListener(View.OnClickListener { v: View? -> callRandom() })
    }

    private fun callRandom() {
        service!!.getRandomNumber().enqueue(object : Callback<Int?> {
            override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                if (response.isSuccessful()) {
                    val resultat = response.body()
                    Log.i("RETROFIT", "Mon nombre aléatoire : " + resultat)
                    binding!!.tvRandom.setText(resultat.toString())
                } else {
                    Log.i("RETROFIT", "J'ai une erreur : " + response.code())
                    Toast.makeText(this@MainActivity, "Y'a un problème!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Int?>, t: Throwable) {
                Log.i("RETROFIT", "J'ai une grosse erreur!" + t.message)
                Toast.makeText(this@MainActivity, "Y'a un gros problème!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}