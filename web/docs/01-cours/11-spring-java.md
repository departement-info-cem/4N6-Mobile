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
- ajouter un controleur
- regarder les recettes
- démarrer le serveur
- déboquer le serveur

Recettes à faire:
- créer un serveur
- ajouter un contrôleur en get
- définir un service et l'injecter
- écrire des tests

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

:::

:::info Séance 4

Maintenant que vous connnaissez bien la structure d'un serveur Spring Boot, vous devez implémenter la suppression de tâche dans le serveur de votre TP3.

:::

</Column>

</Row>

:::note Exercices de la semaine

## Exercice démarrer un serveur

1. Créez un projet Spring Boot avec Spring Initializr
2. En vous inspirant de la recette [Spring Boot 2](https://info.cegepmontpetit.ca/4N6-Mobile/recettes/spring-02-controleur), ajoutez un contrôleur qui retourne une chaîne de caractères
3. Utiliser le navigateur pour accéder à l'adresse http://localhost:8080/

## Exercice Post et PostMan

- en suivant la recette [Spring Boot 3](https://info.cegepmontpetit.ca/4N6-Mobile/recettes/spring-03-post-service-jpa), ajoutez un controleur sur l'url "/prout/envoyer" en méthode Post
- on souhaite récupérer un paramètre **taille** entier dans la query ("/prout/envoyer?taille=150") optionnel
- une string **texte** est envoyée dans le corps (**body**) de la requête

Le tout renvoie **texte** répété autant de fois que nécessaire pour atteindre **taille**. Par exemple:
- texte est "pipo"
- taille vaut 10
- le contrôleur renvoie la string "pipopipopi"

Pour tester votre code, utiliser Postman pour déclencher le contrôleur et valider votre code


## Exercice calcul et tests 

Dans cet exercice, on veut:
- que tu montes un serveur dans un dossier **CalculServeur**
- que tu écrives une classe de service qui sera injectée dans le contrôleur avec une méthode 
```java showLineNumbers
public BigInteger fibonacci(int n){} 
```
- que tu écrives des tests unitaires pour cette méthode: on s'attend à ce que ça torche même si n est dans les 1000-2000
- que tu implantes un contrôleur qui prend **n** en paramètre et renvoie le nième nombre de Fibonacci (tu chercheras comment calculer ce nombre)
- que tu testes ton contrôleur avec Postman
- l'url doit être **/fibo/10** pour 10 par exemple

## Exercice toutes les méthodes

Dans cet exercice, on veut:
- que tu montes un serveur dans un dossier **CalculMethodes**
- que tu implantes une méthode dans ton contrôleur pour chaque méthode HTTP
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


## Exercice préparatoire : Trace client-serveur

**Contexte** : Le code suivant met en place une interaction client-serveur entre une application Android et un serveur Spring Boot. L’objectif est de permettre l’affichage des détails d’une tâche à partir de son identifiant (id). L’interaction est initiée lorsque l’utilisateur sélectionne une tâche dans l'application.

Code client :

```kotlin
public class TaskDetailResponse {
    public Long id;
    public String name;
    public Date deadline;
    public List<ProgressEvent> events;
    public int percentageDone;
    public double percentageTimeSpent;
}


interface Service {
  @GET("/api/detail/{id}")
  fun detail(@Path("id") id: Long) : Call<TaskDetailResponse>
}


object RetrofitUtil {
    private var instance: Service? = null
    fun get(): Service {
        if (instance == null) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(CustomGson.getIt()))
                .client(client())
                .baseUrl("http://10.0.2.2:8080/")
                .build()
            instance = retrofit.create<Service>(Service::class.java)
            return instance!!
        } else{
            return instance!!
        }
    }

    private fun client(): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(SessionCookieJar)
            .build()
    }

}


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.getLongExtra("id", 0)

        displayDetails(id)
    }


    private fun displayDetails(id : Long){

        val service: Service = RetrofitUtil.get() // Décrire uniquement l'effet global de cette ligne

        service.detail(id).enqueue(object : Callback<TaskDetailResponse> {
            override fun onResponse(call: Call<TaskDetailResponse>, response: Response<TaskDetailResponse>) {

                if (response.isSuccessful) {
                    binding.tvTaskName.text = response.body()!!.name
                } else {
                    Log.i("REPONSE CODE", response.code().toString())
                    Log.i("REPONSE ERREUR", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<TaskDetailResponse>, t: Throwable) {
                Snackbar.make(binding.root, R.string.network_error, Snackbar.LENGTH_LONG).show()
            }
        })

    }

}

```

Code serveur :

```java
@Controller
public class ControllerTask {

    @Autowired
    private ServiceTask serviceTask;

    @GetMapping("/api/detail/{id}")
    public @ResponseBody TaskDetailResponse detail(@PathVariable long id) {
        System.out.println("KICKB SERVER : Detail  with cookie ");
        MUser user = currentUser(); // Décrire uniquement l'effet global de cette ligne
        return serviceTask.detail(id, user);
    }

    private MUser currentUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Le nom utilisateur est " + username);
        UserDetails ud = (UserDetails) authentication.getPrincipal();
        return serviceTask.userFromUsername(ud.getUsername());
    }
}
```


Étant donné les premières étapes suivantes, produisez la trace d'exécution décrivant les principales étapes de la communication client-serveur:
1. Le serveur Spring Boot est lancé localement;
2. L'utilisateur démarre l'application Android;
3. L'utilisateur s'inscrit et crée une première tâche.  De retour à l'écran d'accueil, il sélectionne la tâche nouvellement créée pour en consulter les détails.

| ligne exécutée | effet                             | pile d'appels |
|-----------------|-----------------------------------|--------|

:::
