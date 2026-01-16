---
title: Révision - Liste
description: Mise en page, Liste
hide_table_of_contents: true
---

# Révisions : Liste Multilingue et Navigation

<Row>

<Column>

:::tip Avant la séance

Revoir le contenu de 3N5 pour 
- les [listes](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste1) 
- une [liste avec navigation](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/liste2)
- le [tiroir de navigation](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/tiroir)
- la [traduction](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/multilingue)

:::

</Column>

<Column>

:::info Séance

Vous devez réaliser les exercices ci-dessous.

Si ce n'est pas terminé, vous devez avancer / compléter les mises en page des différents écrans de votre projet.

Vous devez commencer la programmation de la liste de votre projet.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Trace Bouton
- Créez un fichier **trace-bouton.md** pour y mettre la réponse à cet exercice.  
- En suivant les instructions de la recette sur les [traces d'exécution](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/aa-produire-une-trace),
- Produisez la trace d'exécution du code suivant **sans exécuter le code** si vous cliquiez 2 fois sur le bouton.
- Qu'est ce qui serait affiché dans logcat après avoir parti l'application et cliqué le bouton 2 fois?

```kotlin showLineNumbers
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        println("N")
        actionBouton()
        println("L")
    }
    private fun actionBouton() {
        println("O")
        setContent {
            Button(onClick = {
                println("H")
                println("O")
            }) {
                Text("Cliquez-moi")
            }
        }
        println("Ë")
    }
}
```

En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.  
Assurez-vous de bien comprendre quelles lignes de code sont exécutées à quel moment et pourquoi.

## Exercices de 3N5

Nous vous conseillons de valider que vous êtes capables de refaire quelques exercices de 3N5.

##  Exercice ListeA

Créez un projet appelé **ListeA** qui affiche une liste contenant 1000 objets que vous générerez.

Pour créer ces objets, vous devez d'abord créer une classe appelée **Secret** qui contient :

- Un champ **nom** de type String
- Un champ **date** de type java.time.LocalDateTime
- Un champ **nbGrand** de type Long

Par exemple, un objet pourrait avoir le nom Objet #1, la date 01/01/2001 00:00:00 et le nombre 1.

Les 3 champs de chaque item doivent être affichés avec la mise en page de votre choix.

:::
