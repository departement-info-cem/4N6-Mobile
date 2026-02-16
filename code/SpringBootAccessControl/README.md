# Contrôle d'Accès Spring Boot - Exercice de Cybersécurité

## ⚠️ AVERTISSEMENT

Ce dépôt contient des exercices de cybersécurité à but pédagogique. **Les techniques et manipulations présentées ici ne doivent jamais être utilisées sans autorisation explicite.** L'utilisation non autorisée de ces méthodes sur des systèmes tiers constitue une infraction pénale passible de poursuites judiciaires.

Ces exercices sont destinés uniquement à :
- L'apprentissage des concepts de sécurité applicative
- La compréhension des vulnérabilités liées au contrôle d'accès
- La mise en pratique de bonnes pratiques de sécurisation

**Utilisez ces connaissances de manière responsable et éthique.**

## Description

Ce dossier présente différents niveaux d'implémentation du contrôle d'accès dans une application Spring Boot de type « Tinder ». Chaque sous-dossier représente une étape progressive dans la sécurisation de l'application (profils + messages privés) :

| Projet | Authentification | Source d'identité | Vérification propriété |
|--------|-----------------|-------------------|----------------------|
| **01-OpenBar** | ❌ Aucune | N/A | ❌ |
| **02-SpringSecurityBasic** | ✅ Session | ⚠️ Paramètre requête (vulnérable) | ❌ |
| **03-SpringSecurityAuth** | ✅ Session | ✅ SecurityContext | ⚠️ Boîte OK, mais `GET /messages/{id}` fuite (IDOR) |
| **04-TestProprio** | ✅ Session | ✅ SecurityContext | ✅ Vérification complète (403) |

## API (identique dans les 4 projets)

### Identité
- `POST /api/id/signup` — Inscription (`{nomUtilisateur, motDePasse}`)
- `POST /api/id/signin` — Connexion (`{nomUtilisateur, motDePasse}`)

### Profils
- `GET /api/profil/{nomUtilisateur}` — Voir un profil
- `POST /api/profil/bio` — Modifier sa bio (`{nomUtilisateur, bio}`)
- `POST /api/profil/orientation` — Modifier son orientation (`{nomUtilisateur, orientation}`)
- `GET /api/profils/{orientation}` — Lister les profils par orientation

### Messages
- `POST /api/messages` — Envoyer un message (`{expediteur, destinataire, contenu}`)
- `GET /api/messages/{id}` — Lire un message par son ID
- `GET /api/messages?utilisateur={nom}` — Lister les messages d'un utilisateur (01, 02 seulement)
- `GET /api/messages` — Lister mes messages (03, 04 — utilise le SecurityContext)

## Scénario de démonstration

### Étape 1 : Préparer les données (dans chaque projet)

On va inscrire 3 utilisateurs (Bob, Alice, Charlie) et envoyer des messages privés entre eux pour avoir des données à exploiter.

```powershell
curl.exe -c cookies_bob.txt -X POST http://localhost:8080/api/id/signup -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"bob\", \"motDePasse\": \"MotDePasse123!\"}'
curl.exe -c cookies_charlie.txt -X POST http://localhost:8080/api/id/signup -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"charlie\", \"motDePasse\": \"MotDePasse123!\"}'
curl.exe -c cookies_alice.txt -X POST http://localhost:8080/api/id/signup -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"alice\", \"motDePasse\": \"MotDePasse123!\"}'
curl.exe -b cookies_bob.txt -X POST http://localhost:8080/api/messages -H "Content-Type: application/json" -d '{\"expediteur\": \"bob\", \"destinataire\": \"alice\", \"contenu\": \"Salut Alice, on se voit ce soir pour Netflix and chill, ma femme a son club lecture?\"}'
curl.exe -b cookies_alice.txt -X POST http://localhost:8080/api/messages -H "Content-Type: application/json" -d '{\"expediteur\": \"alice\", \"destinataire\": \"bob\", \"contenu\": \"Ou la la! CT chaud caliente hier soir? Pas trop de problème avec ta femme!!\"}'
curl.exe -b cookies_alice.txt -X GET http://localhost:8080/api/messages?utilisateur=alice 
```

### Étape 2 : Tester les vulnérabilités

#### 01-OpenBar — Aucune authentification requise
```powershell
# N'importe qui peut lire n'importe quel message, sans connexion
curl.exe "http://localhost:8080/api/messages?utilisateur=bob"
# → Retourne toute la boîte de réception de Bob devrait être privée, mais elle est publique !
```

#### 02-SpringSecurityBasic — Identité basée sur le paramètre requête
```powershell
# Se connecter en tant que Charlie
curl.exe -c cookies_charlie.txt -X POST http://localhost:8080/api/id/signin -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"charlie\", \"motDePasse\": \"MotDePasse123!\"}'

# Charlie lit la boîte de Bob en changeant le paramètre URL
curl.exe -b cookies_charlie.txt "http://localhost:8080/api/messages?utilisateur=bob"

# Charlie envoie un message en se faisant passer pour Alice
curl.exe -b cookies_charlie.txt -X POST http://localhost:8080/api/messages -H "Content-Type: application/json" -d '{\"expediteur\": \"alice\", \"destinataire\": \"bob\", \"contenu\": \"Tout est fini entre nous lol!!!\"}'
```

#### 03-SpringSecurityAuth — IDOR (Insecure Direct Object Reference)
```powershell
# Se connecter en tant que Charlie
curl.exe -c cookies_charlie.txt -X POST http://localhost:8080/api/id/signin -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"charlie\", \"motDePasse\": \"MotDePasse123!\"}'

# La boîte de réception est correcte — Charlie ne voit que ses messages
curl.exe -b cookies_charlie.txt http://localhost:8080/api/messages

# MAIS Charlie peut lire n'importe quel message par ID
curl.exe -b cookies_charlie.txt http://localhost:8080/api/messages/1
```

#### 04-TestProprio — Sécurisé

Tous les exploits précédents sont corrigés.

```powershell
# Se connecter en tant que Charlie
curl.exe -c cookies_charlie.txt -X POST http://localhost:8080/api/id/signin -H "Content-Type: application/json" -d '{\"nomUtilisateur\": \"charlie\", \"motDePasse\": \"MotDePasse123!\"}'

# Charlie tente de lire le message privé Alice→Bob
curl.exe -b cookies_charlie.txt http://localhost:8080/api/messages/1

# Alice peut lire son propre message
curl.exe -b cookies_alice.txt http://localhost:8080/api/messages/1
```

## Objectifs Pédagogiques

À travers ces différentes implémentations, vous apprendrez :

1. **Les risques d'une absence de contrôle d'accès** (01-OpenBar) — Tout est public
2. **Le danger de faire confiance à l'identité fournie par la requête** (02-SpringSecurityBasic) — JSESSIONID existe mais ignoré
3. **L'IDOR (Insecure Direct Object Reference)** (03-SpringSecurityAuth) — Identité correcte, mais pas de vérification de propriété
4. **Le contrôle d'accès complet** (04-TestProprio) — Identité + propriété vérifiées
