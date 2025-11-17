package ca.cem.composeretrofitbase.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Gestionnaire de cookies pour maintenir la session HTTP
 * Stocke les cookies reçus du serveur et les renvoie automatiquement
 * dans les requêtes suivantes
 */
object SessionCookieJar : CookieJar {

    private var cookies: MutableList<Cookie> = mutableListOf()

    /**
     * Sauvegarde les cookies reçus dans la réponse HTTP
     */
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies.toMutableList()
    }

    /**
     * Charge les cookies à envoyer dans la requête HTTP
     */
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}
