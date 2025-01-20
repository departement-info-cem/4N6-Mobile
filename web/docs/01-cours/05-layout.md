---
title: DrawerLayout, CoordinatorLayout, LinearLayout, AppBarLayout
description: DrawerLayout CoordinatorLayout LinearLayout AppBarLayout
hide_table_of_contents: true
---

# Rencontre DrawerLayout, CoordinatorLayout, LinearLayout, AppBarLayout

<Row>

<Column>

:::tip Avant la séance (2h)

Vous devez vous familiariser avec le [projet Layouts](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Layouts).

Regardez la vidéo ci-dessous sur l'ordre des layout pour le tiroir de navigation

<Video url="https://www.youtube.com/watch?v=bmIs1LAYfCc" />

:::

</Column>

<Column>

:::info Séance

Nous verrons comment faire une vue scrollable au niveau du layout. On discutera du cas où on a un recyclerView dans notre mise en page principale.

Nous allons regarder ensemble comment mettre en place [une appbar](https://developer.android.com/training/appbar/setting-up). Nous verrons comment faire explicitement (en l'ajoutant dans le layout) et comment faire en choisissant un thème avec appbar.

Nous discuterons de la structure des Layouts dans l'activité.

- DL puis CL puis AppBarLayout puis LinearLayout
- CL puis DL puis AppBarLayout
- AppBarLayout puis DL

:::

</Column>

</Row>

:::note Exercices de la semaine

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
