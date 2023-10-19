---
title: Erreurs GUI
description: Erreurs - GUI - SnackBar/Toast, Localisés, I18N
hide_table_of_contents: true
---

# Erreurs dans l'interface graphique

<Row>

<Column>

:::tip Avant la séance (2h)

Vous pouvez lire la doc d'Android sur les différents moyens d'afficher des erreurs dans un formulaire [ici](https://material.io/archive/guidelines/patterns/errors.html#errors-user-input-errors).

Vous pouvez également regarder la doc technique sur les [snackbars](https://material.io/components/snackbars/android#using-snackbars) et les [dialogues](https://material.io/components/dialogs#behavior).

Vous vous familiariserez avec les démos de la semaine [affichage d'erreurs](https://github.com/departement-info-cem/4N6-Mobile/tree/master/Demonstrations/AffichageErreur) et [erreurs dans retrofit](https://github.com/departement-info-cem/4N6-Mobile/tree/master/Demonstrations/ErreurRetrofit).

:::

</Column>

<Column>

:::info Séance

On discutera des différents moyens d'afficher un message d'erreur: Toast, Snackbar, dialogues et erreur sur un champ texte.

On discutera du cas où il peut y avoir plusieurs erreurs au cours d'un seul appel au serveur comme dans un formulaire avec de nombreux champs. Quelle stratégie adopter: envoyer seulement la première erreur à l'utilisateur ou toutes les erreurs.

On montrera comment traduire les messages d'erreur dans l'interface.

On discutera du cas où l'erreur vient de l'absence de signal ou de réseau dans l'application.

:::

</Column>

</Row>

:::note Exercices

### Exercice Toast Snackbar

Copier un de vos exercices qui envoyait une requête à un web service.

Vous devez modifier votre application pour afficher soit le résultat si ça a fonctionné dans un **Snackbar** soit une erreur si il n'y a pas de réseau (vous testerez avec l'appareil en mode avion) dans un **Toast**.

### Exercice un errorBody mais pas deux

En reprenant l'exemple [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/master/Demonstrations/ErreurRetrofit), Essaie d'afficher deux fois le résultat de la méthode `response.errorBody().string()` dans logcat.

Il devrait y avoir un comportement bizarre, discute avec ton prof pour expliquer/comprendre ce qui se passe.

:::
