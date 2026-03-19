---
title: Intégration
description: Intégration client
hide_table_of_contents: true
---

# Intégration

Pendant les séances d'intégration, il n'y a pas de nouveau contenu. Le but est de consolider ce que tu as vu dans les séances précédentes.

:::info Exercices à compléter.

Tu dois compléter le TP2.

:::

:::note Exercice complémentaire
## Exercice préparatoire : Trace client-serveur

**Contexte** : Le code suivant met en place une interaction client-serveur entre une application Android et un serveur Spring Boot. L’objectif est de permettre l’affichage des détails d’une tâche à partir de son identifiant (id). L’interaction est initiée lorsque l’utilisateur sélectionne une tâche dans l'application.

Code client :

```kotlin showLineNumbers
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

```java showLineNumbers
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


Étant données les premières étapes suivantes, produisez la trace d'exécution décrivant les principales étapes de la communication client-serveur:
1. Le serveur Spring Boot est lancé localement;
2. L'utilisateur démarre l'application Android;
3. L'utilisateur s'inscrit et crée une première tâche.  De retour à l'écran d'accueil, il sélectionne la tâche nouvellement créée pour en consulter les détails.

| ligne exécutée | effet                             | pile d'appels |
|-----------------|-----------------------------------|--------|

:::