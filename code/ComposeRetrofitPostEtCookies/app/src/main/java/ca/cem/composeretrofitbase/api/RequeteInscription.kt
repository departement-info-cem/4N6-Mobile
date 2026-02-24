package ca.cem.composeretrofitbase.api

/**
 * Classe de requête pour l'inscription d'un nouvel utilisateur
 */
data class RequeteInscription(
    val nom: String,
    val motDePasse: String,
    val confirmationMotDePasse: String,
)
