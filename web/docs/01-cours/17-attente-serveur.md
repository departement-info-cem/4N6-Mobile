---
title: Attente serveur
description: Gestion de l'attente serveur - dialogues d'attentes
hide_table_of_contents: true
---

# Gestion de l'attente dans l'interface graphique

<Row>

<Column>

:::tip Avant la séance (2h)

Vous devrez regarder l'exemple d'application fournie pour les indicateurs de progression **[ici](https://github.com/departement-info-cem/4N6-Mobile/tree/master/Demonstrations/Progress)**. Il s'agit du projet Progress dans le repository du cours.

OPTIONNEL : Regardez l'exemple de Google concernant les listes avec [swipe to refresh](https://developer.android.com/training/swipe/add-swipe-interface) **[ici](https://github.com/android/views-widgets-samples/tree/main/SwipeRefreshLayoutBasic)**. Vous pouvez le lancer dans Android Studio, aller à `File > New > Import sample` et taper "swipe" dans le champ de recherche.

:::

</Column>

<Column>

:::info Pendant la séance

On discutera du besoin d'indiquer à l'utilisateur qu'un accès réseau est en cours pour éviter de la frustration et le fait que l'utilisateur envoie plusieurs fois une même requête.

On discutera les besoins d'avoir une action de rafraîchissement, en particulier si les informations d'une page peuvent être modifiées par un tiers.

On regardera le code des exemples **[ici](https://github.com/departement-info-cem/4N6-Mobile/tree/master/Demonstrations/SwipeRefresh)** en cours. En particulier, on prendra l'exemple  et on identifiera les éléments importants pour un swipe to refresh.

:::

</Column>

</Row>

:::note Exercices

### Exercice AttenteSimple

Créez un projet appelé **AttenteSimple** avec un bouton et un textview avec un fond vert.

Faites la programmation nécessaire pour que quand on appuie sur le bouton, le textview soit remplacé par un indicateur de progression pendant une seconde puis que le textview revienne.

### Exercice AttenteListe

Créez un projet appelé **AttenteListe**.

Implantez un "tirer pour rafraichir" et modifier le contenu de la liste à chaque fois avec un ensemble de 100 nombres entiers tirés au hasard entre 333 et 444.