package org.sabourin.swiperefresh

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.addAll
import kotlin.text.clear

class MainActivity : AppCompatActivity() {
    var swiperefresh: SwipeRefreshLayout? = null
    var adapter: TrucAdapteur? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        swiperefresh = findViewById(R.id.swiperefresh)
        swiperefresh?.setOnRefreshListener {
            mettreAJour()
        }

        // premier appel pour aller chercher la liste
        mettreAJour()
    }

    private fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        adapter = TrucAdapteur()
        recyclerView.adapter = adapter
    }

    private fun remplirRecycler(body: List<Truc?>) {
        adapter!!.list.clear()
        adapter!!.list.addAll(body)
        adapter!!.notifyDataSetChanged()
    }


    private fun mettreAJour() {
        swiperefresh?.isRefreshing = true
        RetrofitUtil.get().vaChercherLaListe().enqueue(object : Callback<List<Truc?>> {
            override fun onResponse(call: Call<List<Truc?>>, response: Response<List<Truc?>>) {
                swiperefresh?.isRefreshing = false

                remplirRecycler(response.body()!!)
            }

            override fun onFailure(call: Call<List<Truc?>>, t: Throwable) {
                swiperefresh?.isRefreshing = false
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                mettreAJour()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.refresh, menu)
        return true
    }
}