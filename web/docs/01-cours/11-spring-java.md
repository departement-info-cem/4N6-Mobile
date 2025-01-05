---
title: Serveur Spring Java
description: Démarrer un serveur Spring Java, explication API existante
hide_table_of_contents: true
---

# Développer un serveur Spring Boot Java

<Row>

<Column>

:::tip Avant la séance

Explorez le [code du serveur de l'application](https://github.com/departement-info-cem/KickMyB-Server)

Vous pouvez également explorer la documentation de [Spring Boot](https://spring.io/projects/spring-boot).

- utiliser spring initializr pour créer un projet
- ajouter un controleur
- regarder les recettes
- démarrer le serveur
- déboquer le serveur

Recettes à faire:
- créer un serveur
- ajouter un contrôleur en get
- définir un service et l'injecter
- écrire des tests

:::

</Column>

<Column>

:::info Séance 1

Nous allons montrer comment partir le serveur. Comment mettre un point d'arrêt. À quel endroit est le code correspondant aux différentes URLs.

Nous allons voir également comment envoyer des requêtes au serveur avec le logiciel Postman.

:::

:::info Séance 2

Tu devras avancer tes exercices sur les serveurs. Tu devrais avoir programmé au moins un contrôleur.

:::

:::info Séance 3

Tu complètes tes exercices. Tu devrais commencer ton 

:::

:::info Séance 4

Nous allons montrer comment partir le serveur. Comment mettre un point d'arrêt. À quel endroit est le code correspondant aux différentes URLs.

Nous allons voir également comment envoyer des requêtes au serveur avec le logiciel Postman.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice démarrer un serveur

1. Créez un projet Spring Boot avec Spring Initializr
2. En vous inspirant de la recette XYZ ajoutez un contrôleur qui retourne une chaîne de caractères
3. Utiliser le navigateur pour accéder à l'adresse http://localhost:8080/

## Exercice Post et PostMan

- en suivant la recette Spring Boot XYZ, ajoute un controleur sur l'url "/prout/envoyer" en méthode Post
- on souhaite récupérer un paramètre **taille** entier dans la query ("/prout/envoyer?taille=150") optionnel
- une string **texte** est envoyée dans le corps (**body**) de la requête

Le tout renvoie **texte** répété autant de fois que nécessaire pour atteindre **taille**. Par exemple:
- texte est "pipo"
- taille vaut 10
- le contrôleur renvoie la string "pipopipopi"

Pour tester votre code, utiliser Postman pour déclencher le contrôleur et valider votre code


## Exercice calcul et tests 

Dans cet exercice, on veut:
- que tu montes un serveur dans un dossier **CalculServeur**
- que tu écrives une classe de service qui sera injectée dans le contrôleur avec une méthode 
```java
public BigInteger fibonacci(int n){} 
```
- que tu écrives des tests unitaires pour cette méthode: on s'attend à ce que ça torche même si n est dans les 1000-2000
- que tu implantes un contrôleur qui prend **n** en paramètre et renvoie le nième nombre de Fibonacci (tu chercheras comment calculer ce nombre)
- que tu testes ton contrôleur avec Postman
- l'url doit être **/fibo/10** pour 10 par exemple


## 👨‍🎓👨‍🎓 Exercice Chic Type (interaction avec Android et Retrofit)

<Row>

<Column size="9">

Le projet [suivant](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ChicType) a un problème. Votre mission sera de trouver le problème et de le corriger.

Lorsqu'on appuie sur le bouton "Obtenir un nombre aléatoire", l'application Android fournie effectue un appel réseau au web service qui se trouve à l'adresse suivante : [https://fourn6-mobile-prof.onrender.com/exos/chic/type/](https://fourn6-mobile-prof.onrender.com/exos/chic/type/). Le nombre aléatoire retourné par le web service devrait s'afficher dans un champ texte.

1. Dans postman, effectuez une requête vers le web service. Notez le type de donnée qui est dans le corps de la réponse.
2. Ouvrez l'application Android fournie dans Android Studio. Essayez d'appuyer sur le bouton.
3. Mettez des breakpoint et consultez le logcat pour tenter de comprendre pourquoi votre application n'arrive pas à traiter le nombre retourné par le web service.
4. Effectuez un correctif pour que l'application fonctionne correctement. Montrez votre correctif à votre enseignant.

</Column>

<Column size="3">

![ChicType](_11-spring/ChicType.png)

</Column>

</Row>

## 👨‍🎓👨‍🎓 Exercice Postman

En roulant le serveur [KickMyB](https://github.com/departement-info-cem/KickMyB-Server), vous devez préparer un ensemble de requêtes avec postman pour créer un compte, accéder à la liste des tâches de l'utilisateur, créer une tâche et mettre à jour une tâche.

:::
