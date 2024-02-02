Un mantra, c'est une phrase qu'on répète souvent pour s'améliorer.

Tout d'abord, comme tout cours de programmation, regarde les mantras de programmation, 
ils sont toujours valides.

## Retrofit

### "Le type de ton Call doit aller avec ce qu'il y a dans la réponse"
Si Retrofit ne peut pas détricoter la réponse HTTP dans le type specifié, ça va casser
- ```Call<String>``` la réponse doit être **"sdkajsk"**
- ```Call<Integer>``` la réponse doit être **9890**
- ```Call<List<...>>``` la réponse JSON doit commencer ressembler à **[    ]**
- ```Call<Pipo>``` la réponse JSON doit commencer ressembler à **{    }**

### "Pour debug un appel Retrofit, 3 points d'arrêts tu poseras"
Il faut :
- un point d'arrêt sur la ligne du enqueue: juste avant le départ de la requête
- un point d'arrêt sur la première ligne du onResponse: c'est le retour de la réponse si tout est beau
- un point d'arrêt sur la première ligne du onFailure: au cas où ça pète, pour voir ce qui casse

## Mise en page avec LineatLayout

### "S'il n'y a qu'un seul widget, tu n'as pas besoin de widget"
Un LinearLayout sert à partager l'espace dispo, s'il n'y a qu'un seul widget, pas besoin de partager

### "S'il y a un weight, alors soit width soit height doit être 0dp"

### "Ne fais pas confiance à la vue design quand tu édites un XML"
Cela rejoint le mantra "Exécute souvent": quand tu modifies un layout, exécutes le code à chaque étape
pour voir le résultat sur un vrai appareil Android

## 

