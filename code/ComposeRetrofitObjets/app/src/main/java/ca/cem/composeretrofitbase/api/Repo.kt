package ca.cem.composeretrofitbase.api

data class Repo(
    val nom: String,
    val puissance: Long,
    val classe: String,
    val equipement: List<String>
)
