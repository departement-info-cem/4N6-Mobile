package ca.cem.composeretrofitbase.api

/**
 * Classe de requête pour la connexion d'un utilisateur
 */
data class RequeteConnexion(
    val username: String,
    val password: String
)
