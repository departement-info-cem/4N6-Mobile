---
title: Erreurs GUI
description: Erreurs - GUI - SnackBar/Toast, Localisés, I18N
hide_table_of_contents: true
---

# Erreurs dans l'interface graphique

<Row>

<Column>

:::tip Avant la séance (2h)

Consultez la recette [Messages d'erreur](https://info.cegepmontpetit.ca/4N6-Mobile/recettes/messages-erreur).

Vous pouvez lire la doc d'Android sur les différents moyens d'afficher des erreurs dans un formulaire [ici](https://material.io/archive/guidelines/patterns/errors.html#errors-user-input-errors).

Vous pouvez également regarder la doc technique sur les [snackbars](https://material.io/components/snackbars/android#using-snackbars) et les [dialogues](https://material.io/components/dialogs#behavior).

Vous vous familiariserez avec les démos de la semaine [affichage d'erreurs](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/AffichageErreur) et [erreurs dans retrofit](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ErreurRetrofit).

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

Vous devez modifier votre application pour afficher soit le résultat si ça a fonctionné dans un **Snackbar** soit une erreur si le serveur est inaccessible (vous testerez avec l'appareil en mode avion) dans un **Toast**.

### Exercice un errorBody mais pas deux

En ouvrant l'exemple [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/ErreurRetrofit), vous devez:
- démarrer le projet
- regarder la trace d'exécution dans Logcat
- on devrait trouver une ligne qui commence par "le corps encore "
- selon le code cela devrait afficher le corps de la réponse HTTP (`response.errorBody().string()`)

Dans un fichier **double-errorBody.md**, indique ce que tu observes, ce que tu comprends et en quoi il faut faire attention:
```md showLineNumbers
# comportement observé du code
bli bla blo
# ce que je comprends de ce qui se passe
bli bla blo
# comment faire pour éviter ce problème (comment accéder 2 fois au corps de la réponse d'erreur)
bli bla blo
```

Valide avec ton prof ce qui se passe et comment réagir.

:::
