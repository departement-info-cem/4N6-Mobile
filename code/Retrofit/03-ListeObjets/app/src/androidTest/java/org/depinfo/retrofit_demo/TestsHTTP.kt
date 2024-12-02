package org.depinfo.retrofit_demo

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.depinfo.retrofit_demo.http.Service
import org.depinfo.retrofit_demo.transfer.Repo
import org.depinfo.retrofit_demo.transfer.Utilisateur
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class TestsHTTP {

    @Test
    fun testRepos() {
        val service: Service = RetrofitUtil.get()
        val call: Call<List<Repo>> = service.listRepos("departement-info-cem")
        val response: Response<List<Repo>> = call.execute()
        val resultat: List<Repo>? = response.body()
        // Test if the list contains a repo whose name contains 3N5 and one containing 4N6
        assert(resultat!!.any { it.name!!.contains("3N5") })
        assert(resultat.any { it.name!!.contains("4N6") })
    }

    @Test
    fun testUtilisateur() {
        val service: Service = RetrofitUtil.get()
        val call: Call<Utilisateur> = service.utilisateur("departement-info-cem")
        val response: Response<Utilisateur> = call.execute()
        val resultat: Utilisateur? = response.body()
        assertEquals(37709816L, resultat!!.id)
    }
}