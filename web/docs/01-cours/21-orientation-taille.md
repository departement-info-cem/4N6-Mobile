---
title: Orientation et Taille
description: Compatibilité - Portrait paysage, taille écran et interface scrollable
hide_table_of_contents: true
---

# Compatibilité, taille et orientation

<Row>

<Column>

:::tip Avant la séance (2h)

Le but de cette séance est de voir si votre application est utilisable sur plusieurs tailles de périphériques et plusieurs orientations.

Vous pouvez essayer une tablette de 10 pouces en portrait/paysage ainsi qu'un petit téléphone en portrait/paysage.

- Est-ce que tous les éléments d'interface sont accessibles ou visibles?
- Si c'est trop petit, comment rendre l'écran scrollable?

Vous pouvez consulter le projet de démonstration [ComposePortraitPaysage](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ComposePortraitPaysage) qui montre comment gérer l'orientation portrait/paysage avec Jetpack Compose.

:::

</Column>

<Column>

:::info Séance

Nous commencerons par un examen papier de 20 minutes (27 minutes si 1/3 temps supplémentaire, 30 si 1/2 temps supplémentaire).

On discutera d'une combinaison de tests qui est minimale : un téléphone, une tablette et des tailles d'écran assez standards.

On discutera des 2 grandes familles de mise en page : scrollable ou pas.

:::

</Column>

</Row>



:::note Exercices

## Exercice Trace Lambda

Créez un fichier **trace-lambda.md** pour y mettre la réponse à cet exercice.
En suivant les instructions de la recette sur les [traces d'exécution](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/aa-produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.

```kotlin showLineNumbers
fun executer(x: Int, operation: (Int) -> Int): Int {
    println("A: $x")
    val res = operation(x + 1)
    println("B: $res")
    return res * 2
}

fun main() {
    val resultat = executer(3) { nombre ->
        println("C: $nombre")
        nombre * 3
    }
    println("D: $resultat")
}
```

En créant un projet et en exécutant en débogage, validez votre trace.
Si vous avez des surprises, demandez des explications à votre prof.

:::
