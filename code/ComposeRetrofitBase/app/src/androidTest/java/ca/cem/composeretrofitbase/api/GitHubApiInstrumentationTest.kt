package ca.cem.composeretrofitbase.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class GitHubApiInstrumentationTest {
    @Test
    fun testListReposContains5a5() {
        Log.d("TEST", "Entrée dans la fonction testListReposContains5a5")
        
        // Appel synchrone au service Retrofit existant
        val call = RetrofitInstance.api.listReposString("jorisdeguet")
        val response = call.execute()

        // Vérifier que la réponse HTTP est réussie
        assertTrue("Response unsuccessful: ${response.code()}", response.isSuccessful)

        val body = response.body() ?: ""

        // Vérifier que la chaîne contient "5a5"
        assertTrue("Body did not contain '5a5'. Body was: $body", body.contains("5a5"))
        
        Log.d("TEST", "Sortie de la fonction testListReposContains5a5")
    }
}

