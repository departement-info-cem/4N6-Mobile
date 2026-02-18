# generate_users.py

Génère un fichier `output.json` contenant les comptes étudiants à charger dans le serveur Omnisus. Chaque compte utilise le mot de passe `pass`.

## Utilisation

### Mode fichier

```bash
python3 generate_users.py fichier.txt
```

Le fichier peut être dans n'importe quel format. Le script extrait automatiquement tous les IDs (séquences de 5 à 20 chiffres consécutifs). Les zéros en début d'ID sont conservés.

Exemple de fichier accepté :

```
2489702, 420.BU
Lawani, Farhane

2490670, 420.BU
Labranche, Christine

2492993, 420.BU
Benleghzal, Rayan
```

### Mode interactif

```bash
python3 generate_users.py
```

Sans argument, le script passe en mode interactif. Collez ou tapez les IDs directement. Les lignes vides sont ignorées. Pour terminer la saisie : **deux lignes vides consécutives** ou **Ctrl+D**.

## Charger les comptes dans le serveur

Le script envoie automatiquement le JSON au serveur à `http://localhost:8080/api/admin/student` après génération. Si le serveur n'est pas accessible, un message d'erreur s'affiche mais le fichier `output.json` est quand même créé.

Pour envoyer manuellement :

```bash
curl -X POST http://localhost:8080/api/admin/student \
  -H "Content-Type: application/json" \
  -H "admin-password: Passw0rd!" \
  -d @output.json
```

> **Attention** : cet appel supprime tous les utilisateurs et notes existants avant de créer les nouveaux comptes.
