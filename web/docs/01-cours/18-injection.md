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

</Column>

<Column>

:::info Séance

On parlera de la confusion entre données et commandes qui arrive par le biais de langages qui ne sont pas compilés mais interprétés et des langages qui permettent un mode interprété.

Paradoxalement, les injections sont aussi les moins susceptibles d'arriver sur des systèmes récents:

- La plupart des frameworks récents contiennent des protections
- Pour injection SQL, quand on utilise un ORM comme Hibernate, SpringData ou EntityFramework, on est protégés
- Pour l'injection Javascript, Angular Vue ou encore React vont par défaut désactiver les commandes JS avant de les inclure dans la page.

Les systèmes exposés sont habituellement:

- Systèmes legacy ou patrimoniaux avec un lien très direct avec SQL ou html
- Systèmes produits par des programmeurs très juniors non sensibilisés

:::

</Column>

</Row>

:::note Exercices

### Injection Javascript

Essayez d'injecter du javascript dans la base de données et regarder si vous pouvez la déclencher sur le /index du serveur KickMyB.

Entrainez-vous à décrire dans un fichier texte, la FAILLE que vous avez découvert, décrivez étape par étape comment en tirer partie dans une session EXPLOIT et dans CORRECTIF, expliquez comment vous avez combler la faille.

### Omnisus (Optionnel) (Avancé)

Ouvrez le projet [suivant](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Omnisus) et prenez le temps de lire le README.

Votre enseignant va exécuter le serveur sur son poste. Votre mission sera de pirater le serveur qui est sur le poste de votre enseignant pour modifier la note qui vous a été attribuée sur la plateforme de gestion de note Omnisus.

Entrainez-vous à décrire dans un fichier texte, la FAILLE que vous avez découvert, décrivez étape par étape comment en tirer partie dans une session EXPLOIT et dans CORRECTIF, expliquez comment vous avez combler la faille.

:::
