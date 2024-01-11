---
title: Révision - Mise en page
description: LinearLayout match_parent wrap_content et weight
hide_table_of_contents: true
---

# Mise en page, LinearLayout

<Row>

<Column>

:::tip Avant la séance (2h)

Vous devez pensez à la mise en page des différents écrans de votre application

- Connexion avec nom d'utilisateur et mot de passe
- Inscription avec nom d'utilisateur et mot de passe
- Liste des tâches (accueil)
- Création d'une nouvelle tâche
- Consultation du détail d'une tâche

Vidéo de révision :

<Video url="https://www.youtube.com/watch?v=x-dQ5Sa0Ns0" />

:::

</Column>

<Column>

:::info Séance

On fera une démo pour donner des idées de mise en page.

Vous devrez compléter les mises en page de vos différentes activités.

:::

</Column>

</Row>

:::note Exercices de la semaine

### Exercice A

Créez un projet appelé **SigninSignup** contenant une activité. Faites un layout avec un champ de saisie pour le courriel, un pour le mot de passe, un bouton d'inscription et un bouton de connexion.

La password doit être caché (avec des points), l'interface doit être claire : on sait quoi taper à quel endroit.

### Exercice B

Téléchargez et roulez ce [projet](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/Proportions).
Nous voulons générer 2 TextViews côte-à-côte, où celui de gauche occupe 100/392 de la largeur de l'écran et celui de droite, 292/392 (environ 1/4 et 3/4).

En utilisant le débogeur graphique (*Layout Inspector*, en bas à droite), inspectez les largeurs de tous les TextViews.
Lesquels ont les proportions voulues?

Quelles sont les différences entre les 3 méthodes utilisées (les 3 couleurs)?
Assurez-vous de bien comprendre ce qui se passe, par exemple pourquoi les 2 TextViews ont les bonnes proportions sur certaines lignes et pas sur d'autres.

### Exercice C

Créez un projet appelé **MiseEnPage** contenant 1 seule activité. reproduisez la mise en page suivante en mode portrait (en dessous de l'actionbar):

![Mise en page](_02-mise-en-page/miseenpage.png)

:::
