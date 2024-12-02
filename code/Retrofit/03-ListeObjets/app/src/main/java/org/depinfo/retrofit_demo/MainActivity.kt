package org.depinfo.retrofit_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.depinfo.retrofit_demo.adapter.RepoAdapter
import org.depinfo.retrofit_demo.databinding.ActivityMainBinding
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.depinfo.retrofit_demo.transfer.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepoAdapter()
        binding.recyclerView.adapter = adapter

        RetrofitUtil.get().listRepos("jorisdeguet").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                adapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val repoList = listOf(
            Repo().apply { name = "Ceci est une liste"; id = 1 },
            Repo().apply { name = "En attendant le retour de Retrofit"; id = 2 }
        )
        adapter.submitList(repoList)
    }
}
