---
title: Contrôle d'accès
description: Sécurité - authentification et contrôle d'accès
hide_table_of_contents: true
---

# Sécurité : contrôle d'accès

<Row>

<Column>

:::tip Avant la séance (2h)

Si on regarde le [top 10](https://owasp.org/www-project-top-ten/) des problèmes de sécurité dans les applis web, on trouve le [contrôle d'accès défaillant](https://owasp.org/Top10/A01_2021-Broken_Access_Control/) en position 1.

C'est aussi la source de failles qu'on observe le plus souvent dans les projet des finissants.

La raison principale est que le contrôle d'accès nécessite souvent du travail sans apporter de nouvelles fonctionnalités.

<Video url="https://www.youtube.com/watch?v=xKUarGFeDyo" />

Vous trouverez ici les différentes branches du code du serveur de démo ici:

- Aucun contrôle d'accès [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/01-OpenBar)
- Spring Security et authentification [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/02-SpringSecurityBasic)
- Spring Security, authentification et extraction de l'utilisateur authentifié courant [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/03-SpringSecurityAuth)
- Spring Security, authentification et extraction de l'utilisateur authentifié courant et test de propriété [ici](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/04-TestProprio)

:::

</Column>

<Column>

:::info Séance

Nous discuterons des différences entre les différents projets.

Nous verrons également comment rédiger une FAILLE, un EXPLOIT et un CORRECTIF sur l'exemple [aucune sécurité](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/01-OpenBar).

- Vérifier si l'utilisateur est authentifié, si ce n'est pas le cas exception
- Si c'est une lecture, il faut ensuite vérifier que l'utilisateur est le propriétaire (ou a le droit d'accès) sur l'objet demandé
- Si c'est une écriture, il faut s'assurer qu'on ne peut pas créer des données dans le compte d'un autre utilisateur

:::

</Column>

</Row>

:::note Exercices

### Exercice 1

Vous devez rédiger FAILLE EXPLOIT et CORRECTIF pour la branche [Authentification](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/02-SpringSecurityBasic).

### (Optionnel) Exercice 2

Vous devez rédiger FAILLE EXPLOIT et CORRECTIF pour la branche [Extraction de l'usager authentifié](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/03-SpringSecurityAuth).

Attention, deux failles existent dans le projet ci-dessus.

### (Bonus) Pour les pros hax0rs

Trouvez la faille restante dans la branche [Test de propriété](https://github.com/departement-info-cem/4N6-Mobile/tree/main/code/SpringBootAccessControl/04-TestProprio).
