---
title: Révision
description: Premier cours, révisions activités, intent, extras
hide_table_of_contents: true
---

# Premier cours, révisions activités


<Row>

<Column>

:::tip Avant la séance

C'est la première séance, pas de travail avant.

Révisions:
- La base : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/cours/6.1-intro-android)
- View Binding : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/view-binding)
- RecyclerView : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/cours/7.1-recycler)
- Multilingue : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/cours/13.2-multilingue)

:::

</Column>

<Column>

:::info Séance

Plan de cours, évaluations et calendrier.

Nous discuterons de l'ensemble de l'application et de comment nous allons procéder.

- Le serveur sera fourni : [repo ici](https://github.com/departement-info-cem/KickMyB-Server)
- Vous devrez programmer l'application Android pour s'y connecter
- Vous programmerez l'interface graphique
- Vous programmerez les appels réseaux pour envoyer / recevoir les infos du serveur

Nous passerons à travers les énoncés des 3 phases du projet et nous expliquerons la phase 1 en détails.

Vous complétez quelques exercices de révisions ainsi que les exercices de compréhension.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Tiroir

Créez un projet appelé **Tiroir** contenant 3 activités. Ajoutez un tiroir de navigation permettant d'accéder à chacune des activités depuis chacune des activités.

## Exercice Navigation

Créez un projet appelé **Navigation** contenant 2 activités.

- La première activité contient un EditText et un bouton
- Quand on appuie sur le bouton, le texte du EditText est envoyé dans un extra de l'Intent à la deuxième activité
- La 2ème activité affiche dans un TextView le texte reçu via l'Intent

## Exercice Trace Simple
Créez un fichier **trace-simple.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](../recettes/produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.
```kotlin
fun main() {
    val a = 5 + 2 * 2
    for (i in 1..2) {
        for (j in 4..6) {
            println((i + j).toString() + (i.toString() + " " + a))
        }
    }
}
```
En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.

## Exercice Trace Portée
Créez un fichier **trace-portee.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](../recettes/produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.
```kotlin
fun main() {
    val a = 5
    val b = 10
    val resultat = calcul(a, b)
}

fun calcul(x: Int, y: Int): Int {
    val z = x + y
    val zz = double(z)
    return zz
}

fun double(n: Int): Int {
    val nombreDouble = n * 2
    return nombreDouble
}
```
En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.

:::
