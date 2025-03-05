---
title: Débogage serveur
description: Debug serveur seul - postman, logs
hide_table_of_contents: true
---

# Débogage serveur

<Row>

<Column>

:::tip Avant la séance (2h)

Déboguer un serveur (ou confirmer qu'il fonctionne bien) vous aidera à préciser davantage d'où provient une erreur. Dans ce cours, nous allons exécuter le [serveur fourni](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/DebugServer) et nous montrerons les étapes pour déboguer la première erreur.

<Video url="https://www.youtube.com/watch?v=O4KVfoFoxAg" />

:::

</Column>

<Column>

:::info Séance

Nous allons discuter des différents cas où on a utilisé des outils de débogage et pourquoi:
- Layout Inspector
- App Inspection > Network Inspector 
- Point d'arrêt / "evaluate expression" 
- 3 points d'arrêts pour appel asynchrone
- h2-console
- Postman
- log et traces 

En démo, on va voir Postman comme notre principal outil pour envoyer des requêtes au serveur que ce soit GET ou POST.

On va voir que les logs (System.out.println, logcat) servent à suivre des sessions de travail et à voir les valeurs quand on ne veut pas arrêter l'exécution avec des breakpoints.

Nous allons déboguer le premier problème dans le projet Serveur ensemble. Le reste en exercice

On parlera rapidement des options de débogage d'un serveur déployé en production : on peut lire les logs, mais on ne peut pas mettre de point d'arrêt.

:::

</Column>

</Row>

:::note Exercice

Vous devez compléter le débogage du projet vu en cours afin de le rendre opérationnel. Voici la liste des requêtes à effectuer dans Postman :

1. Indiquez le problème.
   - localhost:8080/req1 en POST avec, dans le Body, un Raw JSON contenant ["Tomate", "Jambon"]
2. Indiquez les lignes de code causant les problèmes et pourquoi selon vous.
   - localhost:8080/req2 en GET
3. Expliquez pourquoi cette requête semble fonctionner, alors que non. Trouvez ensuite quelle méthode cause problème.
   - localhost:8080/req3 en GET

:::
