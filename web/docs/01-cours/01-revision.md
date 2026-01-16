---
title: Révision
description: Premier cours, révisions écrans, navigation, i18n
hide_table_of_contents: true
---

# Premier cours, révisions écrans


<Row>

<Column>

:::tip Avant la séance

C'est la première séance, pas de travail avant.

Révisions:
- La base : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/cours/6.1-intro-android)
- Liste : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste1) et [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste2)
- Multilingue : [ici](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/multilingue)

:::

</Column>

<Column>

:::info Séance

- **20 min** : examen diagnostic.
- **10 min** : correction par les pairs.
- **5 min** : objectifs du cours.
- **5 min** : présentation du plan de cours: examens papier, examens ordi, TP.
- **5 min** : présentation du [TP1](../tp/tp1). 
- reste de la séance : exercices de révision

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Trace Simple
Créez un fichier **trace-simple.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/aa-produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code**.
```kotlin showLineNumbers
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

## Exercice Tiroir

Créez un projet appelé **Tiroir** contenant 3 écrans. Ajoutez un tiroir de navigation permettant d'accéder à chacun des écrans depuis chacun des écrans.

## Exercice Navigation

Créez un projet appelé **Navigation** contenant 2 écrans.

- Le premier écran contient un champ texte et un bouton
- Quand on appuie sur le bouton, le texte du champ est envoyé vers le 2ème écran
- Le 2ème écran affiche le texte reçu

:::
