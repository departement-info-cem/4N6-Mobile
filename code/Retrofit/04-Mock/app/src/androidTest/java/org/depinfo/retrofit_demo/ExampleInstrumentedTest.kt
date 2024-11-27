package org.depinfo.retrofit_demo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testSimple() {
        val service = RetrofitUtil.get()
        val call = service.listReposString("departement-info-cem")
        val response = call.execute()
        val resultat = response.body()
        Log.i("RETROFIT", resultat!!)
    }

    @Test
    fun testStructures() {
        val service = RetrofitUtil.get()
        val call = service.listRepos("departement-info-cem")
        val response = call.execute()
        val resultat = response.body()
        Log.i("RETROFIT", resultat.toString())
    }

    @Test
    fun testSimpleUtilisateur() {
        val service = RetrofitUtil.get()
        val call = service.utilisateurString("departement-info-cem")
        val response = call.execute()
        val resultat = response.body()
        Log.i("RETROFIT", resultat!!)
    }

    @Test
    fun testSimpleUtilisateurStructure() {
        val service = RetrofitUtil.get()
        val call = service.utilisateur("departement-info-cem")
        val response = call.execute()
        val resultat = response.body()
        Log.i("RETROFIT", resultat.toString())
    }
}