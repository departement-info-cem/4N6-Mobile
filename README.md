# Cours de 4N6 en docusaurus

Fait à partir du modèle de base pour un cours du département d'informatique du CÉGEP Édouard-Montpetit. 

L'objectif est de générer un site web pour un cours le plus simplement possible. Les pages de contenu sont écrites en Markdown.

## Pour bien commencer

1. Sur Github, faite un *fork* de ce projet. Le standard de nomenclature au département est `sigle-nom-du-cours` ex : `4N6-Mobile`.
2. Clonez et ouvrez le projet forké dans votre éditeur de code préféré.
3. Dans les paramètres de votre repository, dans la section **Pages**, dans la sous section **Build and deployment**, **Branch**, sélectinonez `gh-pages` et `/ (root)`, puis cliquez sur  le bouton **Save**
4. Le fichier `config.json` doit être modifié pour contenir les informations liées à votre cours. `nomUrl` doit correspondre au nom du repository Github créé.
5. Voir [Installation](#installation) et [Développement Local](#développement-local) pour voir comment démarrer le serveur
6. Modifiez les documents Markdown qui sont dans la répertoire `docs` selon vos besoins.
7. Profit

## Installation

```
$ npm install
```

## Développement Local

```
$ npm start
```

Cette commande démarre une serveur de développement local sur le port `3000` de votre machine personnelle et ouvre un navigateur avec l'adresse locale du site. Les changements effectués sur la documentation (`/docs`) sont automatiquement appliqués sur le site à la sauvegarde des fichiers. Les changements faits à la configuration (ex: `docusaurus.config.js`) nécessitent un redémarrage du projet.

## Déploiement

Tout le code poussé sur la branche `main` de ce dépôt est automatiquement déployée sur [https://info.cegepmontpetit.ca/nom-du-repo-github/](https://info.cegepmontpetit.ca/nom-du-repo-github/) à l'aide de Github Pages et Github Actions.

## Références

- [Guide Markdown de base](https://www.markdownguide.org/getting-started/)
- [Guide Markdown étendu pour Docusaurus](https://docusaurus.io/fr/docs/markdown-features)