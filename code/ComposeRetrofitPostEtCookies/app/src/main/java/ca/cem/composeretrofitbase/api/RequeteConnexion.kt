package ca.cem.composeretrofitbase.api

/**
 * Classe de requête pour la connexion d'un utilisateur
 */
data class RequeteConnexion(
    val nom: String,
    val motDePasse: String
)
