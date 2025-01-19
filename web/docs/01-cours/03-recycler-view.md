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

Vous devrez réaliser l'exercice ci-dessous

Vous devrez avancer / compléter les mises en page des différentes activités du TP1

Vous devrez commencer la programmation du recyclerView du TP1

:::

</Column>

</Row>

:::note Exercices de la semaine

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

## Exercice Trace Boutons
Créez un fichier **trace-boutons.md** pour y mettre la réponse à cet exercice.  
Soient ces bouts de code dans ces deux fichiers :

<Tabs queryString="recette-string">
  <TabItem value="activity_main.xml" label="activity_main.xml">
    ```xml
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Plus 1"
            android:id="@+id/boutonPlus" />
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Fois 3"
            android:id="@+id/boutonTriple" />
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal"
            android:id="@+id/nombreAffiche" />
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Afficher le nombre à jour"
            android:id="@+id/boutonAfficher" />
    ```
  </TabItem>
  <TabItem value="MainActivity.kt" label="MainActivity.kt">

    ```kotlin
    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
    
        private var a: Int = 0
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            afficherA()
            actionPlus()
            actionTriple()
            actionAfficher()
        }
        private fun afficherA() {
            binding.nombreAffiche.text = a.toString()
        }
        private fun actionPlus() {
            binding.boutonPlus.setOnClickListener {
                var a = a + 1
                afficherA()
                this.a = a
            }
        }
        private fun actionTriple() {
            binding.boutonTriple.setOnClickListener {
                var a = a * 3
                afficherA()
                this.a = a
            }
        }
        private fun actionAfficher() {
            binding.boutonAfficher.setOnClickListener {
                afficherA()
            }
        }
    }
    ```

  </TabItem>
</Tabs>

En suivant les instructions de la recette sur les [traces d'exécution](../recettes/produire-une-trace),
produisez la trace d'exécution (**sans exécuter le code**) lorsque vous effectuez les opérations suivantes :
1. cliquer le bouton "Plus 1"
2. cliquer le bouton "Plus 1"
3. cliquer le bouton "Afficher le nombre à jour"
4. cliquer le bouton "Fois 3"
5. cliquer le bouton "Fois 3"
6. cliquer le bouton "Afficher le nombre à jour"

En créant un projet et en exécutant en débogage, validez votre trace.  
Si vous avez des surprises, demandez des explications à votre prof.

:::
