package ca.cegepmontpetit.4n6.composeportraitpaysage

data class Cours(
    val nom: String,
    val no: String,
    val session: Int,
    val sessionDecBac: Int? = null,
    val bac: Boolean,
    val icons: List<String>,
    val url: String? = null,
    val heures: Int? = null,
    val profil: String? = null
)

object CoursProvider {
    val coursList = listOf(
        Cours(
            nom = "Bureautique",
            no = "420-1B3",
            session = 1,
            sessionDecBac = 1,
            bac = false,
            icons = listOf("fa fa-file-word", "fa fa-file-excel"),
            heures = 45
        ),
        Cours(
            nom = "Systèmes d'exploitation",
            no = "420-1X6",
            session = 1,
            sessionDecBac = 1,
            bac = false,
            icons = listOf("fab fa-linux", "fab fa-windows"),
            heures = 90
        ),
        Cours(
            nom = "Introduction à la programmation",
            no = "420-1N6",
            session = 1,
            sessionDecBac = 1,
            bac = false,
            icons = listOf("fas fa-code", "fas fa-hashtag"),
            url = "https://info.cegepmontpetit.ca/1P6/",
            heures = 90
        ),
        Cours(
            nom = "Réseaux locaux",
            no = "420-1C5",
            session = 1,
            sessionDecBac = 1,
            bac = false,
            icons = listOf("fas fa-network-wired"),
            heures = 75
        ),
        Cours(
            nom = "Mathématiques pour Informatique",
            no = "201-924",
            session = 2,
            sessionDecBac = 2,
            bac = false,
            icons = listOf("fas fa-calculator"),
            heures = 60
        ),
        Cours(
            nom = "Introduction aux bases de données",
            no = "420-2D5",
            session = 2,
            sessionDecBac = 1,
            bac = false,
            icons = listOf("fas fa-database"),
            heures = 75
        ),
        Cours(
            nom = "Programmation orientée objet",
            no = "420-2P6",
            session = 2,
            sessionDecBac = 2,
            profil = "prog",
            bac = false,
            icons = listOf("fab fa-git", "fas fa-hashtag"),
            heures = 90
        ),
        Cours(
            nom = "Programmation en TI",
            no = "420-2T6",
            session = 2,
            profil = "reseau",
            bac = false,
            icons = listOf("fab fa-git", "fa-brands fa-python"),
            heures = 90
        ),
        Cours(
            nom = "Prog. Web serveur",
            no = "420-2W5",
            profil = "prog",
            session = 2,
            sessionDecBac = 2,
            bac = false,
            icons = listOf("fab fa-html5", "fas fa-exchange-alt"),
            heures = 75
        ),
        Cours(
            nom = "Serveurs intranet",
            no = "420-2X5",
            profil = "reseau",
            session = 2,
            bac = false,
            icons = listOf("fas fa-server"),
            heures = 75
        ),
        Cours(
            nom = "Communiquer en milieux professionnels",
            no = "201-924",
            session = 3,
            sessionDecBac = 4,
            bac = false,
            icons = listOf("fas fa-comments"),
            heures = 60
        ),
        Cours(
            nom = "Introduction à la cybersécurité",
            no = "420-3S4",
            url = "https://info.cegepmontpetit.ca/3U4-cybersec/",
            session = 3,
            bac = false,
            icons = listOf("fas fa-shield-alt"),
            heures = 60
        ),
        Cours(
            nom = "Introduction à la programmation mobile",
            no = "420-3M5",
            profil = "prog",
            url = "https://departement-info-cem.github.io/3N5-Prog3/",
            session = 3,
            sessionDecBac = 4,
            bac = false,
            icons = listOf("fa fa-mobile-android", "fab fa-android"),
            heures = 75
        ),
        Cours(
            nom = "Prog. Web transactionnelle",
            profil = "prog",
            no = "420-3W6",
            session = 3,
            sessionDecBac = 3,
            bac = false,
            icons = listOf("fas fa-file-code", "fas fa-database"),
            url = "https://departement-info-cem.github.io/3W6-Web-Transactionelle/",
            heures = 90
        ),
        Cours(
            nom = "Automatisation de tâches",
            no = "420-3T5",
            profil = "reseau",
            url = "https://info.cegepmontpetit.ca/3t5-automatisation/",
            session = 3,
            bac = false,
            icons = listOf("fas fa-robot"),
            heures = 75
        ),
        Cours(
            nom = "Réseaux d'entreprise",
            no = "420-3C5",
            profil = "reseau",
            session = 3,
            bac = false,
            icons = listOf("fas fa-network-wired"),
            heures = 75
        ),
        Cours(
            nom = "Serveurs internet",
            no = "420-3X6",
            profil = "reseau",
            session = 3,
            bac = false,
            icons = listOf("fas fa-at", "fas fa-database"),
            heures = 90
        ),
        Cours(
            nom = "Méthodologie et gestion de projet",
            no = "420-4G3",
            session = 4,
            bac = false,
            icons = listOf("fas fa-people-carry"),
            heures = 45
        ),
        Cours(
            nom = "Solutions applicatives",
            no = "420-4N4",
            profil = "prog",
            session = 4,
            bac = false,
            icons = listOf("fab fa-searchengin", "far fa-lightbulb"),
            heures = 60
        ),
        Cours(
            nom = "Bases de données et programmation Web ",
            no = "420-4D6",
            profil = "prog",
            session = 4,
            sessionDecBac = 5,
            bac = false,
            icons = listOf("fas fa-database"),
            url = "https://info.cegepmontpetit.ca/4D5-Base-De-Donnees-Et-Prog-Web/",
            heures = 90
        ),
        Cours(
            nom = "Applications mobiles",
            no = "420-4M6",
            profil = "prog",
            session = 4,
            sessionDecBac = 5,
            bac = false,
            icons = listOf("fa fa-mobile-android", "fab fa-java"),
            url = "https://github.com/departement-info-cem/4N6-Mobile/",
            heures = 90
        ),
        Cours(
            nom = "Prog. Web orientée services",
            no = "420-4W6",
            profil = "prog",
            session = 4,
            sessionDecBac = 4,
            bac = false,
            icons = listOf("fab fa-angular"),
            url = "https://info.cegepmontpetit.ca/4W6-WebServices/",
            heures = 90
        ),
        Cours(
            nom = "Architecture et cybersécurité",
            no = "420-4U5",
            profil = "reseau",
            session = 4,
            bac = false,
            icons = listOf("fas fa-shield-alt", "fas fa-dungeon"),
            heures = 75
        ),
        Cours(
            nom = "Solutions d'infrastructure TI",
            no = "420-4L4",
            profil = "reseau",
            session = 4,
            bac = false,
            icons = listOf("far fa-lightbulb"),
            heures = 60
        ),
        Cours(
            nom = "Réseaux étendus",
            no = "420-4C5",
            profil = "reseau",
            session = 4,
            bac = false,
            icons = listOf("fas fa-globe-americas"),
            heures = 75
        ),
        Cours(
            nom = "Administration centralisée",
            no = "420-4X6",
            profil = "reseau",
            session = 4,
            bac = false,
            icons = listOf("fas fa-server"),
            heures = 90
        ),
        Cours(
            nom = "Professions et soutien aux utilisateurs",
            no = "420-5F4",
            session = 5,
            bac = false,
            icons = listOf("fas fa-user-tie"),
            heures = 60
        ),
        Cours(
            nom = "Design d'interface",
            no = "582-905",
            session = 5,
            profil = "prog",
            sessionDecBac = 4,
            bac = false,
            icons = listOf("fas fa-palette", "far fa-object-group"),
            heures = 75
        ),
        Cours(
            nom = "Analyse et conception d'applications",
            no = "420-5Y5",
            profil = "prog",
            session = 5,
            bac = false,
            icons = listOf("fas fa-clipboard-list"),
            heures = 75
        ),
        Cours(
            nom = "Applications mobiles avancées",
            no = "420-5M6",
            profil = "prog",
            session = 5,
            sessionDecBac = 6,
            bac = false,
            icons = listOf("fas fa-globe-americas"),
            url = "https://departement-info-cem.github.io/5N6-mobile-2/",
            heures = 90
        ),
        Cours(
            nom = "Programmation web avancée",
            no = "420-5W6",
            profil = "prog",
            session = 5,
            sessionDecBac = 5,
            bac = false,
            icons = listOf("fab fa-angular", "fas fa-hashtag"),
            url = "https://departement-info-cem.github.io/5W5-Web-Avancee/",
            heures = 90
        ),
        Cours(
            nom = "Cybersécurité et surveillance TI",
            no = "420-5S5",
            profil = "reseau",
            session = 5,
            bac = false,
            icons = listOf("far fa-eye"),
            heures = 75
        ),
        Cours(
            nom = "Virtualisation et infonuagique",
            no = "420-5U6",
            profil = "reseau",
            session = 5,
            bac = false,
            icons = listOf("fas fa-layer-group"),
            heures = 90
        ),
        Cours(
            nom = "Serveurs de communication",
            no = "420-5X6",
            profil = "reseau",
            session = 5,
            bac = false,
            icons = listOf("fas fa-mail-bulk"),
            heures = 90
        ),
        Cours(
            nom = "Serveurs de base de données",
            no = "420-5D5",
            profil = "reseau",
            session = 5,
            bac = false,
            icons = listOf("fas fa-database"),
            heures = 75
        ),
        Cours(
            nom = "Stage",
            no = "420-6GE",
            session = 6,
            bac = false,
            icons = listOf("fas fa-user-tie"),
            heures = 225
        ),
        Cours(
            nom = "Projet en TI",
            no = "420-6X5",
            profil = "reseau",
            session = 6,
            bac = false,
            icons = listOf("fas fa-users", "fas fa-people-carry"),
            heures = 120
        ),
        Cours(
            nom = "Projet en développement d'applications",
            no = "420-6P8",
            profil = "prog",
            session = 6,
            bac = false,
            icons = listOf("fas fa-users", "fas fa-people-carry"),
            url = "https://info.cegepmontpetit.ca/projet-prog/",
            heures = 120
        )
    )
}