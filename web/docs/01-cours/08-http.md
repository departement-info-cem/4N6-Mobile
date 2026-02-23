---
title: HTTP
description: HTTP dans Android, service et DTO
hide_table_of_contents: true
---

# Accès réseau client et asynchronisme

<Row>

<Column>

:::tip Avant la séance : théorie (2h)

Nous allons utiliser les recettes suivantes :
- [Appel de base](../recettes/retrofit-01-base)
- [Appel dans l'interface graphique](../recettes/retrofit-02-objet)
- [Appel avec liste d'objets](../recettes/retrofit-03-liste)
- [Envoi d'objet en POST et cookies](../recettes/retrofit-04-cookie)


:::

</Column>

<Column>

:::info Séance 1

On expliquera comment envoyer une requête HTTP aux web services des exercices et réceptionner le résultat. On utilisera **RETROFIT**.

On réitérera l'importance des tests et des points d'arrêt en appels synchrones et asynchrones.

On parlera des cas où on a besoin de récupérer des données une seule fois à l'ouverture de la page ou si on a besoin de faire des mises à jour régulièrement. 

:::

:::info Séance 2

On expliquera comment démarrer le serveur du TP2:
- dans IntelliJ, ouvrir le projet **KickMyB-Server**
- depuis powershell, se placer dans le dossier du projet puis `.\mvnw spring-boot:run`

On expliquera à quoi servent les différentes classes de transfert dans la **[bibliothèque de DTO ou classes de transfert](https://github.com/departement-info-cem/KickMyB-Library)**. On montrera comment inclure la bibliothèque dans votre projet:

- Ajout du repository [Jitpack](https://jitpack.io/) (permet d'accéder à un projet Github sans qu'il soit déployé sur un repo Maven)
- Ajout de la librairie et format de la dépendance : 'com.github.**compte-github**:**repo-github:hash-du-commit**'
- Exemple à la ligne 31 [ici](https://github.com/departement-info-cem/KickMyB-Server/blob/e0ac94e8d75921f83fd1302ac415cb4c81cb9794/build.gradle#L31)

:::

:::warning Séance 3

Souvent, les classes de modèle ont beaucoup de champs, car la base de données garde énormément d'information. 

Mais pour plusieurs raisons (sécurité, espace, etc.), le client n'a pas besoin de toutes ces informations.

On utilise alors des classes de transfert (**DTO**) qui permettent de transférer seulement l'information utile entre le client et le serveur.

:::

</Column>

</Row>

:::note Exercices de la semaine

##  Exercice formatif

En préparation de l'examen de la prochaine séance, assurez-vous de savoir effectuer des appels réseau avec Retrofit et d'afficher les résultats dans une interface graphique.

##  Exercice RetrofitSimple
Créez un projet appelé **RetrofitSimple**. Le web service [ici](https://fourn6-mobile-prof.onrender.com/exos/long/double/4) prend un nombre et retourne son double.

Vous devez démontrer un appel avec succès à ce web service dans une application Android depuis l'interface graphique.

Par exemple, un écran avec un OutlinedTextField pour le nombre et un bouton pour déclencher l'appel. Au retour de la valeur (doublée), celle-ci est affichée soit dans un texte sur l'écran, soit dans un SnackBar.

## Exercice RetrofitComplexe

Créez un projet appelé **RetrofitComplexe**. Le web service [ici](https://fourn6-mobile-prof.onrender.com/exos/truc/complexe?name=whippsie) prend un nom et retourne un objet complexe (remplacez whippsie par votre prénom dans l'URL).

Vous devez démontrer un appel avec succès à ce web service dans une application Android depuis l'interface graphique.

Par exemple, un écran avec un Text qui affiche les données retournées par l'appel.

##  Exercice RetrofitListes

Créez un projet appelé **RetrofitListes**. Le projet doit démarrer un écran coupé en 2 dans le sens de la hauteur. Chaque moitié est occupée par un LazyColumn qui sera peuplé par les appels suivants :

- [Webservice 1](https://fourn6-mobile-prof.onrender.com/exos/long/list) renvoie une liste de Long. Chaque élément dans le LazyColumn est un simple champ texte où vous affichez la valeur du nombre.
- [Webservice 2](https://fourn6-mobile-prof.onrender.com/exos/truc/list) renvoie une liste d'objets complexes. Chaque item du LazyColumn doit afficher les 2 champs simples ainsi que la taille de la liste.

##  Exercice RetrofitEnvoiPost

Créez un projet appelé **RetrofitEnvoiPost**.

Tu vas envoyer une requête 
- en méthode POST 
- à l'URL [https://fourn6-mobile-prof.onrender.com/exos/truc/doubler](https://fourn6-mobile-prof.onrender.com/exos/truc/doubler) 
- avec comme corps de la requête un objet du format suivant
```json showLineNumbers
{
  "a": 100,
  "b": "gndsadsa",
  "c": [
    8,
    12,
    14,
    18,
    1998
  ]
}
```
- le type de retour sera le même format que celui envoyé.

Tu peux afficher le résultat sur l'interface graphique du téléphone ou programmer directement un test unitaire synchrone.

## Exercice RetrofitGithub

Créez un projet appelé **RetrofitGithub** dans lequel vous créerez un service simple en GET sur l'URL [https://api.github.com/users/departement-info-cem/repos](https://api.github.com/users/departement-info-cem/repos).

Chaque élément du LazyColumn doit afficher les champs "name" et "description" ainsi qu'un bouton qui mène à l'URL (champ html_url) (doit fonctionner).

Une fois que ça fonctionne, appuyez sur votre bouton pour accéder à la page du cours 4N6-Mobile, cliquez sur le lien bleu `info.cegepmontpetit.ca/4N6-Mobile/` et naviguez le site pour retrouver cet exercice-ci! ♻️

##  DTO - Exercice de compréhension

Dans cet exercice, on vous donne la classe de modèle et on cherche les champs qu'on va mettre dans la classe de transfert (**PersonneDTO**).

L'interface graphique à droite montre les informations nécessaires à l'application.

Créez un fichier **dto.md** dans votre repo exercice pour garder votre réponse avec le format suivant :
```md showLineNumbers
# PersonneDTO
- ID: Long
- pipo: String (justification de pourquoi le champ est dans le DTO)
- etc.
```

<Row>

<Column>

![Tinder 1](_06-http/dto_tinder.png)

</Column>

<Column>

![Tinder 2](_06-http/dto_tinder2.png)

</Column>

</Row>

##  DTO - Exercice de compréhension 2

On reprend l'exercice précédent mais avec maintenant deux classes modèles. On cherche les champs qu'on va mettre dans la classe de transfert (**CompteDTO**) toujours avec la même interface graphique.


Créez un fichier **dto2.md** dans votre repo pour garder votre réponse avec le format suivant :
```md showLineNumbers
# PersonneDTO
- ID: Long
- pipo: String (justification de pourquoi le champ est dans le DTO)
- etc.
```

<Row>

<Column>

![Tinder 3](_06-http/dto_tinder3.png)

</Column>

<Column>

![Tinder 2](_06-http/dto_tinder2.png)

</Column>

</Row>


:::
