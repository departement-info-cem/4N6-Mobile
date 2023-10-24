---
title: Erreurs HTTP
description: Erreurs - HTTP, codes (200, 400), exceptions
hide_table_of_contents: true
---

# HTTP réponses et codes d'erreur, exceptions

<Row>

<Column>

:::tip Avant la séance (2h)

Vous trouverez [plusieurs exemples](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/HTTPResponse) de serveur qui utilisent des éléments différents d'HTTP pour représenter les erreurs.

:::

</Column>

<Column>

:::info Séance

On discutera de la gestion des erreurs côté serveur et des différentes stratégies

- Envoyer la première ou toutes les erreurs (surtout pour les long formulaires)
- Utiliser le code d'erreur, le message ou le corps de la réponse
- Dans quelle langue doit être le message d'erreur, traduit sur le serveur ou dans la langue de programmation et traduit sur le client

On expliquera la stratégie utilisée par le serveur fourni dans le cours.

:::

</Column>

</Row>

:::note Exercices

### Exercice (optionnel bonus)

Vous devez faire une copie du projet serveur des profs et voir comment construire une réponse d'erreur HTTP en y indiquant un code 499 et un corps avec la String "Ayoye".
