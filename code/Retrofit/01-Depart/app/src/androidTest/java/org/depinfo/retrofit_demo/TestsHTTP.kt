package org.depinfo.retrofit_demo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class TestsHTTP {

    @Test
    fun testSimple() {
        val service = RetrofitUtil.get()
        val call = service.listReposString("departement-info-cem")
        val response : Response<String> = call.execute()
        val resultat : String? = response.body()
        Log.i("RETROFIT", resultat!!)
        // On vérifie qu'il y a bien le repo de 4N6
        assert(resultat!!.contains("4N6"))
    }
}