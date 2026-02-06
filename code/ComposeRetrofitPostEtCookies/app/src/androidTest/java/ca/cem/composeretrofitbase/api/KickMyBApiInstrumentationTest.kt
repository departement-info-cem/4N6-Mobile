package ca.cem.composeretrofitbase.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class KickMyBApiInstrumentationTest {

    // IMPORTANT: Ouvrir https://kickmyb-server.onrender.com/ dans un navigateur
    // pour réveiller le serveur avant de lancer les tests

    @Test
    fun testInscriptionPuisAccueil() {
        Log.d("TEST", "Entrée dans la fonction testInscriptionPuisAccueil")
        
        val random = (0..100000).random()
        val requete = RequeteInscription(
            username = "test$random@pipo.org",
            password = "123456"
        )

        // Appel inscription
        val appelInscription = RetrofitInstance.api.inscription(requete)
        val reponseInscription = appelInscription.execute()

        assertTrue("Inscription échouée: ${reponseInscription.code()}", reponseInscription.isSuccessful)
        
        val corpsInscription = reponseInscription.body() ?: ""
        assertTrue("La réponse d'inscription ne contient pas le nom d'utilisateur", 
            corpsInscription.contains("test$random@pipo.org"))

        // Appel accueil avec le cookie de session
        val appelAccueil = RetrofitInstance.api.accueilTache()
        val reponseAccueil = appelAccueil.execute()

        assertTrue("Accueil échoué: ${reponseAccueil.code()}", reponseAccueil.isSuccessful)
        
        val corpsAccueil = reponseAccueil.body()
        assertNotNull("La réponse d'accueil est nulle", corpsAccueil)
        
        Log.d("TEST", "Sortie de la fonction testInscriptionPuisAccueil")
    }

    @Test
    fun testConnexionPuisAccueil() {
        Log.d("TEST", "Entrée dans la fonction testConnexionPuisAccueil")
        
        val random = (0..100000).random()
        
        // D'abord créer un compte
        val requeteInscription = RequeteInscription(
            username = "user$random@test.org",
            password = "motdepasse123"
        )
        RetrofitInstance.api.inscription(requeteInscription).execute()

        // Ensuite se connecter avec ce compte
        val requeteConnexion = RequeteConnexion(
            username = "user$random@test.org",
            password = "motdepasse123"
        )
        val appelConnexion = RetrofitInstance.api.connexion(requeteConnexion)
        val reponseConnexion = appelConnexion.execute()

        assertTrue("Connexion échouée: ${reponseConnexion.code()}", reponseConnexion.isSuccessful)

        // Vérifier l'accès à l'accueil avec le cookie de session
        val appelAccueil = RetrofitInstance.api.accueilTache()
        val reponseAccueil = appelAccueil.execute()

        assertTrue("Accueil échoué après connexion: ${reponseAccueil.code()}", reponseAccueil.isSuccessful)
        
        Log.d("TEST", "Sortie de la fonction testConnexionPuisAccueil")
    }
}

