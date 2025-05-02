---
title: Sécurité - injection SQL, injection javascript
description: Sécurité - injection SQL, injection javascript
hide_table_of_contents: true
---

# Sécurité - injection SQL, injection javascript

<Row>

<Column>

:::tip Avant la séance (2h)

Les injections (SQL et javascript) sont souvent les failles de sécurité qui viennent les premières en tête.

<Video url="https://www.youtube.com/watch?v=je2xjYPOqZU" />

Code avec les vulnérabilités en mode passoire **[ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootInjection/02-Passoire)**.

Code avec les correctifs **[ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootInjection/03-SansInjection)**.

:::

:::info Séance

### Encryption / hachage

On discutera de différentes possibilités cryptographiques et de leurs cas d'utilisation.

- SSL et encryption asymétrique
- Encryption symétrique et champs de données sensibles (NAS, carte de crédit)
- Fonction de hash et mots de passe

Les solutions de crypto sont en général difficiles à coder et très sensibles aux erreurs. Il s'agit donc ici de les utiliser correctement, en comprenant ce qu'on fait.
:::

</Column>

<Column>

:::info Séance

### Injections

On parlera de la confusion entre données et commandes qui arrive par le biais de langages qui ne sont pas compilés mais interprétés et des langages qui permettent un mode interprété.

Paradoxalement, les injections sont aussi les moins susceptibles d'arriver sur des systèmes récents:

- La plupart des frameworks récents contiennent des protections
- Pour injection SQL, quand on utilise un ORM comme Hibernate, SpringData ou EntityFramework, on est protégés
- Pour l'injection Javascript, Angular Vue ou encore React vont par défaut désactiver les commandes JS avant de les inclure dans la page.

Les systèmes exposés sont habituellement:

- Systèmes legacy ou patrimoniaux avec un lien très direct avec SQL ou html
- Systèmes produits par des programmeurs très juniors non sensibilisés
- 
:::

</Column>

</Row>

:::note Exercices

### Injection Javascript

Essayez d'injecter du javascript dans la base de données du serveur KickMyB et regardez si vous pouvez le déclencher sur le /index.

Dans un fichier texte **InjectionJavascript.md**, entrainez-vous à :
- Décrire la FAILLE que vous avez découverte.
- Dans une session EXPLOIT, décrire étape par étape comment en tirer partie.
- Et comme CORRECTIF, expliquer comment vous avez comblé la faille.

### Exercice EncrySym
Sur votre serveur KickMyB, en utilisant l'url `http://localhost:8080/h2-console` dans un navigateur, 
vous pouvez ouvrir une console qui permet d'explorer la base de données.

Vous devez trouver le champ encrypté symétrique. Vous devez créer un fichier **Symetrique.md** dans votre repo, dans lequel vous expliquerez :
- Quel champ dans quelle classe a été encrypté?
- Est-ce que c'était nécessaire d'encrypter?
- Y a-t-il un ou plusieurs champ(s) dans le projet qu'on devrait encrypter?

### Exercice HacherNePasHacher
En cherchant dans le projet KickMyB, vous devez trouver où se trouve la configuration du "password encoder". 
Vous devez créer un fichier **EncodageMotDePasse.md** dans votre repo, où vous expliquerez :

- Où se trouvait la configuration à modifier?
- Qu'est-ce que ça change dans l'application?
- Comment en pratique on peut voir la différence, avec quel outil?

### Omnisus (Optionnel) (Avancé)

Ouvrez le projet [suivant](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Omnisus) et prenez le temps de lire le README.

Votre enseignant va exécuter le serveur sur son poste. Votre mission sera de pirater le serveur qui est sur le poste de votre enseignant pour modifier la note qui vous a été attribuée sur la plateforme de gestion de note Omnisus.

Dans un fichier texte **Omnisus.md** :
- Décrivez la FAILLE que vous avez découverte.
- Dans une session EXPLOIT, décrivez étape par étape comment en tirer partie.
- Et comme CORRECTIF, expliquez comment vous avez comblé la faille.

:::
