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
- ajouter un contrôleur
- regarder les recettes
- démarrer le serveur
- déboguer le serveur

Recettes à faire:
- créer un serveur  ([Recette](../03-recettes/spring-01-base.mdx))
- ajouter un contrôleur en get ([Recette](../03-recettes/spring-02-controleur.mdx))
- définir un service et l'injecter ([Recette](../03-recettes/spring-03-post-service-jpa.mdx))
- écrire des tests ([Recette](../03-recettes/spring-03-post-service-jpa.mdx))

:::

</Column>

<Column>

:::info Séance 1

Nous allons montrer comment partir le serveur. Comment mettre un point d'arrêt. À quel endroit est le code correspondant aux différentes URLs.

Nous allons voir également comment envoyer des requêtes au serveur avec le logiciel Postman.

:::

:::info Séance 2

Vous devrez avancer vos exercices sur les serveurs. Vous devriez avoir programmé au moins un contrôleur.

:::

:::info Séance 3 

Complétez l'exercice préparatoire: Trace client-serveur. Développez la trace dans un fichier .md et montrez-la au professeur avant de quitter! Au besoin, référez-vous à la recette "Produire une trace d'exécution".


Maintenant que vous connaissez bien la structure d'un serveur Spring Boot, vous êtes desormais en mesure d'implémenter la suppression de tâche dans le serveur dans le cadre du TP3.

:::

</Column>

</Row>

::::note Exercices de la semaine

## Exercice démarrer un serveur

1. Créez un projet Spring Boot avec Spring Initializr
2. En vous inspirant de la recette [Spring Boot 2](https://info.cegepmontpetit.ca/4N6-Mobile/recettes/spring-02-controleur), ajoutez un contrôleur qui retourne une chaîne de caractères
3. Utiliser le navigateur pour accéder à l'adresse http://localhost:8080/

## Exercice Post et PostMan

- en suivant la recette [Spring Boot 3](https://info.cegepmontpetit.ca/4N6-Mobile/recettes/spring-03-post-service-jpa), ajoutez un contrôleur sur l'url "/prout/envoyer" en méthode Post
- on souhaite récupérer un paramètre **taille** entier dans la query ("/prout/envoyer?taille=150") optionnel
- une string **texte** est envoyée dans le corps (**body**) de la requête

Le tout renvoie **texte** répété autant de fois que nécessaire pour atteindre **taille**. Par exemple:
- texte est "pipo"
- taille vaut 10
- le contrôleur renvoie la string "pipopipopi"

Pour tester votre code, utiliser Postman pour déclencher le contrôleur et valider votre code.




## Exercice calcul et tests 

Dans cet exercice, on veut:
- que tu montes un serveur dans un dossier **CalculServeur**
- que tu écrives une classe de service qui sera injectée dans le contrôleur avec une méthode 
```java showLineNumbers
public BigInteger fibonacci(int n){} 
```
- que tu écrives des tests unitaires pour cette méthode: on s'attend à ce que ça torche même si n est dans les 1000-2000
- que tu implémentes un contrôleur qui prend **n** en paramètre et renvoie le nième nombre de Fibonacci (tu chercheras comment calculer ce nombre)
- que tu testes ton contrôleur avec Postman
- l'url doit être **/fibo/10** pour 10 par exemple

## Exercice toutes les méthodes

Dans cet exercice, on veut:
- que tu montes un serveur dans un dossier **CalculMethodes**
- que tu implémentes une méthode dans ton contrôleur pour chaque méthode HTTP
  - un GET sur l'url "/api/chose" qui renvoit une liste de 10 choses (10 string)
  - un POST sur l'url "/api/chose" qui ajoute une chose à la liste
  - un PUT sur l'url "/api/chose/\{id\}" qui ne fait rien
  - un DELETE sur l'url "/api/chose" qui vide la liste
- Valide que chaque appel est fonctionnel avec POSTMAN

## Exercice de compréhension

Etant donné le code suivant pour un contrôleur Spring Boot

```java showLineNumbers
@Controller
public class ComprehensionController {

  int i = 0;
  static int j = 0;

  @GetMapping(value = "/incremente")
  public @ResponseBody String incremente() {
    i++;
    j++;
    return i + " " + j;
  }

  @GetMapping(value = "/incremente/{valeur}")
  public @ResponseBody String incremente(@PathVariable int valeur) {
    i = i + valeur;
    j = j + valeur;
    return i + " " + j;
  }

  @GetMapping(value = "/consulte")
  public @ResponseBody String consulte() {
    return i + " " + j;
  }
}
```

Dans un fichier **HTTP-comprehension.md** et sans lancer le serveur, indiquez ce qui sera affiché dans le navigateur après chaque requête si on appelle les URLs suivantes:
- http://localhost:8080/incremente
- http://localhost:8080/incremente
- http://localhost:8080/incremente/3
- http://localhost:8080/consulte

Ensuite tu peux programmer un serveur avec ce contrôleur pour valider tes réponses. S'il y a des différences, n'hésite pas à:
- explorer avec le débogueur côté serveur pour mieux voir ce qui se passe
- demander au prof s'il reste des éléments mystérieux


## Trace côté réseau

Voici le code d'un contrôleur : 

```java showLineNumbers
public class RequeteLivres {
    public String auteur;
    public Integer anneeDebut;
    public Integer anneeFin;
}

public class Livre {
    public String titre;
    public String auteur;
    public Integer anneePublication;

     public Livre(String titre, String auteur, Integer anneePublication) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
    }
}

@Controller
public class TraceControlleur {

    public ServiceLivre serviceLivre;

    @GetMapping(value = "/bonjour/{nom}")
    public @ResponseBody String bonjour(@PathVariable String nom){
        return "Bonjour " + nom;
    }

    @GetMapping(value="/suitepaire/{n}")
    public @ResponseBody List<Long> suitePaire(@PathVariable int n){
        List<Long> suite = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            suite.add((long) (i * 2));
        }
        return suite;
    }

    @GetMapping(value="/suitenombre/{n}")
    public @ResponseBody List<Long> suiteNombre(
            @PathVariable int n,
            @RequestParam long premierTerme,
            @RequestParam long deuxiemeTerme){
        List<Long> suite = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(i == 0){
                suite.add(premierTerme);
            } else if(i == 1){
                suite.add(deuxiemeTerme);
            } else {
                long termeSuivant = suite.get(i-1) + suite.get(i-2);
                suite.add(termeSuivant);
            }
        }

        return suite;
    }

    @PostMapping(value="/livres")
    public @ResponseBody List<Livre> envoyerLivres(@RequestBody RequeteLivres requete) {

        List<Livre> livres =serviceLivre.recupererLivres(requete.auteur, requete.anneeDebut, requete.anneeFin); // Décrire ce que fait cette ligne dans sa globablité

        return livres;
    }
}
```

Faites la trace pour chacun des cas suivants : 
 * On reçoit une requête avec l'URL suivante : *http://monsiteweb.com/bonjour/theophile*
 * On reçoit une requête avec l'URL suivante : *http://monsiteweb.com/suitepaire/4*
 * On reçoit une requête avec l'URL suivante : *http://monsiteweb.com/suitenombre/3/premierTerme=3&deuxiemeTerme=5*

Enfin, faites la trace du prochain cas en tenant compte des éléments suivants : 
* On envoie une requête avec l'URL suivante : *http://monsiteweb.com/livres*
* On interroge le serveur pour obtenir l'ensemble des publications de Franck Herbert entre 1970 et 1972
* Les publications correspondantes sont les suivantes :
    * "Dune" publiée aux Éditions Robert Laffont en 1970 
    * "Le Messie de Dune" publié aux Éditions Robert Laffont en 1972

::::
