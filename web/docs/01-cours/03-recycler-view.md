---
title: Révision - Recycler view
description: Mise en page, RecyclerView
hide_table_of_contents: true
---

# Révisions : RecyclerView Multilingue et Navigation

<Row>

<Column>

:::tip Avant la séance

Revoir le contenu de 3N5 pour 
- les [recycler view](https://info.cegepmontpetit.ca/3N5-Prog3/cours/7.1-recycler) 
- les [recettes correspondantes](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/recycler-view-01-string)
- le [tiroir de navigation](https://info.cegepmontpetit.ca/3N5-Prog3/cours/11.1-tiroir)
- la [recette correspondante](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/tiroir)
- la [traduction](https://info.cegepmontpetit.ca/3N5-Prog3/cours/13.2-multilingue)
- la [recette correspondante](https://info.cegepmontpetit.ca/3N5-Prog3/recettes/multilingue) 

:::

</Column>

<Column>

:::info Séance

Vous devez réaliser les exercices ci-dessous.

Si ce n'est pas terminé, vous devez avancer / compléter les mises en page des différentes activités de votre projet.

Vous devez commencer la programmation du recyclerView de votre projet.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice Trace Bouton
Créez un fichier **trace-bouton.md** pour y mettre la réponse à cet exercice.  
En suivant les instructions de la recette sur les [traces d'exécution](../recettes/produire-une-trace),
produisez la trace d'exécution du code suivant **sans exécuter le code** si vous cliqueriez 2 ou 3 fois sur le bouton.

```kotlin showLineNumbers
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "A", Toast.LENGTH_SHORT).show()
        actionBouton()
        Toast.makeText(this, "B", Toast.LENGTH_SHORT).show()
    }

    private fun actionBouton() {
        Toast.makeText(this, "C", Toast.LENGTH_SHORT).show()
        
        binding.bouton.setOnClickListener {
            Toast.makeText(this, "D", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "E", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this, "F", Toast.LENGTH_SHORT).show()
    }
}
```

En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.  
Assurez-vous de bien comprendre quelles lignes de code sont exécutées à quel moment et pourquoi.

## Exercices de 3N5

Nous vous conseillons de valider que vous êtes capables de refaire quelques exercices de 3N5.

## 👨‍🎓👨‍🎓 Exercice RecyclerViewA

Créez un projet appelé **RecyclerViewA** qui affiche un recyclerView contenant 1000 objets que vous générerez avec une liste.

Pour créer ces objets, vous devez d'abord créer une classe appelée **Secret** qui contient :

- Un champ **nom** de type String
- Un champ **date** de type java.time.LocalDateTime
- Un champ **nbGrand** de type Long

Par exemple, un objet pourrait avoir le nom Objet #1, la date 01/01/2001 00:00:00 et le nombre 1.

Les 3 champs de chaque item doivent être affichés avec la mise en page de votre choix.

:::
