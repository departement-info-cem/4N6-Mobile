---
title: HTTP
description: HTTP dans Android, service et DTO
hide_table_of_contents: true
---

# Accès réseau client et asynchronisme

<Row>

<Column>

:::tip Avant la séance : théorie (2h)

Vous devez regarder les videos suivantes:

<Video url="https://www.youtube.com/watch?v=wsF3t33jN8Y" />

**[Code](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Retrofit/01-Depart)**

<Video url="https://www.youtube.com/watch?v=rs8aSam5FT8" />

**[Code](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Retrofit/02-GuiAsync)**

<Video url="https://www.youtube.com/watch?v=CQo8CPBvlvw" />

**[Code](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Retrofit/03-Deboguage)**

<Video url="https://www.youtube.com/watch?v=DbB5Ja33jic" />

:::

</Column>

<Column>

:::info Séance 1

On expliquera comment envoyer une requête HTTP aux web services des exercices et réceptionner le résultat. On utilisera **RETROFIT**.

On réitérera l'importance des tests et des points d'arrêt en appels synchrones et asynchrones

:::

:::warning Séance 2

Souvent, les classes de modèle ont beaucoup de champs, car la base de données garde énormément d'information. Mais pour plusieurs raisons (sécurité, espace, etc.), le client n'a pas besoin de toutes ces informations.

On utilise alors des classes de transfert (**DTO**) qui permettent de transférer seulement l'information utile entre le client et le serveur.

On expliquera à quoi servent les différentes classes de transfert dans la **[librairie de DTO ou classes de transfert](https://github.com/departement-info-cem/KickMyB-Library)** .On montrera comment inclure la librairie dans votre projet:

- Ajout du repository [Jitpack](https://jitpack.io/) (permet d'accéder à un projet Github sans qu'il soit déployé sur un repo Maven)
- Ajout de la librairie et format de la dépendance : 'com.github.**compte-github**:**repo-github:hash-du-commit**'
- Exemple à la ligne 31 [ici](https://github.com/departement-info-cem/KickMyB-Server/blob/e0ac94e8d75921f83fd1302ac415cb4c81cb9794/build.gradle#L31)

:::

</Column>

</Row>

:::note Exercices de la semaine

## 🧑‍🎓🧑‍🎓 Retrofit - Exercice simple

Créez un projet appelé **Simple**. Le web service [ici](https://4n6.azurewebsites.net/exos/long/double/4) prend un nombre et retourne son double.

Vous devez démontrer un appel avec succès à ce web service dans une application Android depuis l'interface graphique.

Par exemple, une activité avec un EditText pour le nombre et un bouton pour déclencher l'appel. Au retour de la valeur (doublée), celle-ci est affichée dans un Toast.

### Retrofit - Exercice Objet complexe

Créez un projet appelé **Complexe**. Le web service [ici](https://4n6.azurewebsites.net/exos/truc/complexe?name=whippsie) prend un nom et retourne un objet complexe (remplacez whippsie par votre prénom dans l'URL).

Vous devez démontrer un appel avec succès à ce web service dans une application Android depuis l'interface graphique.

Par exemple, une activité avec un TextView qui affiche les données retournées par l'appel.

### Retrofit - Exercice Listes

Créez un projet appelé **Listes**. Le projet doit partir une activité coupée en 2 dans le sense de la hauteur. Chaque moitié est occupée par un recyclerView qui sera peuplée par des appels suivants:

- Webservice [1](https://4n6.azurewebsites.net/exos/long/list) Renvoie une liste de Long, chaque élément dans le recyclerView est un simple champ text où vous affichez la valeur du nombre
- Webservice [2](https://4n6.azurewebsites.net/exos/truc/list) Renvoie une liste d'objets complexes. Chaque item du recyclerView doit afficher les 2 champs simple ainsi que la taille de la liste

### Retrofit - Exercice Github API

Créez un projet appelé **Repos** dans lequel vous créerez un service simple en GET sur l'URL [https://api.github.com/users/departement-info-cem/repos](https://api.github.com/users/departement-info-cem/repos)

### DTO - Exercice de compréhension

Dans cet exercice, on vous donne la classe de modèle et on cherche les champs qu'on va mettre dans la classe de transfert (**PersonneDTO**)

L'interface à droite montre les informations nécessaires à l'application.

Créez la classe de transfert avec les champs choisis. Il n'est pas nécessaire de faire l'interface

<Row>

<Column>

![Tinder 1](_06-http/dto_tinder.png)

</Column>

<Column>

![Tinder 2](_06-http/dto_tinder2.png)

</Column>

</Row>

### DTO - Exercice de compréhension 2

On reprend l'exercice précédent mais avec maintenant deux classes modèles. On cherche les champs qu'on va mettre dans la classe de transfert (**CompteDTO**) toujours avec la même interface.

Créez la classe de transfert avec les champs choisis. Il n'est pas nécessaire de faire l'interface

<Row>

<Column>

![Tinder 3](_06-http/dto_tinder3.png)

</Column>

<Column>

![Tinder 2](_06-http/dto_tinder2.png)

</Column>

</Row>

:::
