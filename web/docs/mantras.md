Un mantra, c'est une phrase qu'on répète souvent pour s'améliorer mais surtout éviter des erreurs bien compliquées à trouver.

Tout d'abord, comme tout cours de programmation, regarde les mantras de programmation, 
ils sont toujours valides.

### "Dans ton activité, un seul setContentView tu auras"

### "Quand ton layout déconnera, le Layout Inspector tu utiliseras"

## Retrofit

### "avec ce qu'il y a dans la réponse, le type de ton Call correspondra "
Si Retrofit ne peut pas détricoter la réponse HTTP dans le type specifié, ça va casser
- ```Call<String>``` la réponse doit être **"sdkajsk"**
- ```Call<Integer>``` la réponse doit être **9890**
- ```Call<List<...>>``` la réponse JSON doit commencer ressembler à **[    ]**
- ```Call<Pipo>``` la réponse JSON doit commencer ressembler à **{    }**

### "Pour déboguer un appel Retrofit, 3 points d'arrêts tu poseras"
Il faut :
- un point d'arrêt sur la ligne du enqueue: juste avant le départ de la requête
- un point d'arrêt sur la première ligne du onResponse: c'est le retour de la réponse si tout est beau
- un point d'arrêt sur la première ligne du onFailure: au cas où ça pète, pour voir ce qui casse

### "errorBody(), une seule fois tu appelleras"

Si on l'appelle plus d'une fois, il rend un résultat vide. Stocke le résultat dans une variable pour
y accéder plusieurs fois.

### "Quand rien tu ne comprendras, le NetworkInterceptor tu ouvriras"

Afin de voir le traffic réseau, on doit ouvrir l'onglet Network Inspector dans la vue App Inspection

## LinearLayout

### "S'il n'y a qu'un seul widget, pas besoin de LinearLayout tu n'auras"
Un LinearLayout sert à partager l'espace dispo, s'il n'y a qu'un seul widget, pas besoin de partager

### "Sur un widget texte, le weight sur le height, tu n'utiliseras pas"

Si on met un widget Text (TextView, EditText, Button ...) il peut devenir trop petit pour afficher le texte, 
par exemple si on le met en paysage. wrap_content est ta solution ici.

### "S'il y a un weight, alors soit width soit height doit être 0dp"

### "Ne fais pas confiance à la vue design quand tu édites un XML"
Cela rejoint le mantra "Exécute souvent": quand tu modifies un layout, exécutes le code à chaque étape
pour voir le résultat sur un vrai appareil Android




