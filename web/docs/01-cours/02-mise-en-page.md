---
title: Révision - Mise en page
description: LinearLayout match_parent wrap_content et weight
hide_table_of_contents: true
---

# Mise en page, LinearLayout

<Row>

<Column>

:::tip Avant la séance (2h)

Vous devez penser à la mise en page des différents écrans de votre application

- Connexion avec nom d'utilisateur et mot de passe
- Inscription avec nom d'utilisateur et mot de passe
- Liste des tâches (accueil)
- Création d'une nouvelle tâche
- Consultation du détail d'une tâche

Regardez la démo [ComposeProportions](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ComposeProportions) pour comprendre comment gérer les proportions en Compose.

Pour les révisions, vous pouvez vous référer à la séance du cours 3N5:
[Mise en page](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/b-mise-en-page)
:::

</Column>

<Column>

:::info Séance

On fera une démo pour donner des idées de mise en page.

Vous complétez les exercices de mise en page ainsi que les exercices de compréhension.

Vous devrez compléter les mises en page des différentes activités de votre projet.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Dessin
Étant donné ce code, dessinez ce à quoi ressemblerait l'activité en mode portrait sur une feuille de papier.
```kotlin showLineNumbers
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TraceS1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Button")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row() {
                            Box(
                                modifier = Modifier
                                    .height(200.dp)
                                    .weight(1f)
                                    .background(color = Color.Blue),
                                content = {}
                            )
                            Text(text= "plop plop plop", modifier = Modifier.weight(2f))
                        }
                    }
                }
            }
        }
    }
}
```

Une fois votre dessin terminé (et pas avant), créez un projet avec cette activité pour valider votre dessin.

## 👨‍🎓🤖 Exercice MiseEnPageA

Créez un projet appelé **MiseEnPageA** contenant une activité. Faites un layout avec un champ de saisie pour le courriel, un pour le mot de passe, un bouton d'inscription et un bouton de connexion.

Le mot de passe doit être caché (avec des points), l'interface doit être claire : on sait quoi taper à quel endroit.

## 👨‍🎓🤖 Exercice MiseEnPageB

Créez un projet appelé **MiseEnPageB** contenant 1 seule activité.
Reproduisez la mise en page suivante en mode paysage : (n'utilisez pas de valeurs absolues, sauf pour du *padding* et des *margins*)

![Mise en page](_02-mise-en-page/miseenpage1.png)

## 👨‍🎓🤖 Exercice MiseEnPageC

Copiez votre projet **MiseEnPageB** dans un dossier **MiseEnPageC**. Modifiez votre projet de l'exercice précédent pour reproduire la mise en page suivante :

![Mise en page](_02-mise-en-page/miseenpage2.png)

## 👨‍🎓🤖 Exercice MiseEnPageD

Copiez votre projet **MiseEnPageC** dans un dossier **MiseEnPageD**. Modifiez votre projet de l'exercice précédent pour reproduire la mise en page suivante :

![Mise en page](_02-mise-en-page/miseenpage3.png)

:::
