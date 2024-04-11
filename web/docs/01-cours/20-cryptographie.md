---
title: Cryptographie
description: Sécurité - cryptographie (HTTP+S), hashing
hide_table_of_contents: true
---

# Sécurité : crypto HTTP(S) et hashing

<Row>

<Column>

:::tip Avant la séance (2h)

La cryptographie, ce sont des moyens de cacher certaines informations à des utilisateurs qui ne devraient pas y avoir accès quand on ne peut pas tout simplement leur en empêcher l'accès.

- Pendant que l'information transite sur le réseau, on ne contrôle pas tout le chemin pris par les données
- Si la BD est accédée par un employé ou si un pirate arrive à faire une copie de la BD

<Video url="https://www.youtube.com/watch?v=r0apzpwCGA4" />

<Video url="https://www.youtube.com/watch?v=V8IGEfWnh2s" />

<Video url="https://www.youtube.com/watch?v=VCjJy7QfWJI" />

:::

</Column>

<Column>

:::info Séance

On discutera de différentes possibilités cryptographiques et de leurs cas d'utilisation.

- SSL et encryption asymétrique
- Encryption symétrique et champs de données sensibles (NAS, carte de crédit)
- Fonction de hash et mode de passe

Les solutions de crypto sont en général diffiles à coder et très sensibles aux erreurs. Il s'agit donc ici de les utiliser correctement, en comprenant ce qu'on fait.

:::

</Column>

</Row>

:::note Exercices

### Exercice A
Sur votre serveur en utilisant l'url `http://localhost:8080/h2-console` vous pouvez ouvrir une console qui permet d'explorer la base de données.

Vous devez trouver le champ encrypté symétrique. Tu dois créer un fichier Symetrique.txt à la racine de ton projet où tu expliqueras :

- Quel champ dans quelle classe a été encrypté ?
- Est-ce que c'était nécessaire d'encrypter ?
- Est-ce qu'il y a un ou plusieurs champ(s) dans le projet qui devraient être encryptés?

### Exercice B
En cherchant dans le projet, vous devez trouver où se trouve la configuration du "password encoder". Tu dois créer un fichier EncodageMotDePasse.txt à la racine de ton projet où tu expliqueras :

- Où se trouvait la configuration à modifier ?
- Qu'est-ce que ça change dans l'application ?
- Comment en pratique tu peux voir la différence, avec quel outil?

:::
