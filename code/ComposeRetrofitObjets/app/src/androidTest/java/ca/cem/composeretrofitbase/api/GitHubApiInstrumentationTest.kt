package ca.cem.composeretrofitbase.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class GitHubApiInstrumentationTest {
    @Test
    fun testListReposContains5a5() {
        // Appel synchrone au service Retrofit existant
        val call = RetrofitInstance.api.listRepos("jorisdeguet")
        val response = call.execute()

        // Vérifier que la réponse HTTP est réussie
        assertTrue("Response unsuccessful: ${response.code()}", response.isSuccessful)

        val repos = response.body() ?: emptyList()

        // Vérifier que la liste contient un repo nommé "5a5"
        assertTrue("Repo list did not contain '5a5'", repos.any { it.name.contains("5a5") })
    }
}

