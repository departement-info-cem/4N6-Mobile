package ca.cem.composeretrofitbase.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Interface API pour KickMyB
 * Documentation complète: https://kickmyb-server.onrender.com/swagger-ui/index.html
 */
interface KickMyBApi {
    
    /**
     * Inscription d'un nouvel utilisateur
     * POST /id/inscription
     */
    @POST("id/inscription")
    fun inscription(@Body requeteInscription: RequeteInscription): Call<String>

    /**
     * Connexion d'un utilisateur existant
     * POST /id/connexion
     */
    @POST("id/connexion")
    fun connexion(@Body requeteConnexion: RequeteConnexion): Call<String>

    /**
     * Déconnexion de l'utilisateur courant
     * POST /id/deconnexion
     */
    @POST("id/deconnexion")
    fun deconnexion(): Call<String>

    /**
     * Récupération de l'accueil des tâches
     * GET /tache/accueil
     */
    @GET("tache/accueil")
    fun accueilTache(): Call<String>
}
