---
title: Utilisation standard d'un Scaffold
description: Utilisation standard d'un Scaffold
hide_table_of_contents: true
---

# Utilisation standard d'un Scaffold

<Row>

<Column>

:::tip Avant la séance (2h)

Vous devez vous familiariser avec la démo [ComposeScaffold](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ComposeScaffold).

:::

</Column>

<Column>

:::info Séance

Tu regarderas la Demo [ComposeScaffold](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ComposeScaffold) pour voir comment le scaffold fonctionne en Compose:
- un TopBar avec un titre et des actions
- un tiroir de navigation qui sort quand on appuie sur le bouton hamburger
- un contenu
- etc.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Trace Portée
Créez un fichier **trace-portee.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/aa-produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.
```kotlin showLineNumbers
fun main() {
    val a = 5
    val b = 10
    val resultat = calcul(a, b)
    println(resultat)
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
````
