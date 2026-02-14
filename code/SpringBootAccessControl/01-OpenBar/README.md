# 01-OpenBar - Application de Profils Sans Contrôle d'Accès

## ⚠️ Niveau de Sécurité : AUCUN

Cette version du serveur **ne possède aucun contrôle d'accès**. N'importe qui peut :
- Modifier le profil de n'importe qui
- Consulter tous les profils
- Accéder aux informations de tous les utilisateurs

**C'est une démonstration de vulnérabilité à des fins pédagogiques uniquement.**

Le serveur doit être démarrée :
```bash
mvn spring-boot:run
```

Ou depuis le dossier du projet:
```bash
./mvnw spring-boot:run
```

## Endpoints API

### Inscription
```bash
curl -X POST http://localhost:8080/api/id/signup \
  -H "Content-Type: application/json" \
  -d '{
    "nomUtilisateur": "alice",
    "motDePasse": "password123"
  }'
```

### Connexion
```bash
curl -X POST http://localhost:8080/api/id/signin \
  -H "Content-Type: application/json" \
  -d '{
    "nomUtilisateur": "alice",
    "motDePasse": "password123"
  }'
```

### Obtenir un profil
```bash
curl -X GET http://localhost:8080/api/profile/alice
```

### Modifier la bio
```bash
curl -X POST http://localhost:8080/api/profil/bio \
  -H "Content-Type: application/json" \
  -d '{
    "nomUtilisateur": "alice",
    "bio": "Passionnée de randonnée et de photographie"
  }'
```

### Modifier l'orientation
```bash
curl -X POST http://localhost:8080/api/profil/orientation \
  -H "Content-Type: application/json" \
  -d '{
    "nomUtilisateur": "alice",
    "orientation": "femmes"
  }'
```

### Lister les profils par orientation
```bash
curl -X GET http://localhost:8080/api/profils/femmes
```

## Scénario de Test Complet

```bash
# 1. Créer trois utilisateurs
curl -X POST http://localhost:8080/api/id/signup \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "alice", "motDePasse": "pass123"}'

curl -X POST http://localhost:8080/api/id/signup \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "bob", "motDePasse": "pass123"}'

curl -X POST http://localhost:8080/api/id/signup \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "claire", "motDePasse": "pass123"}'

# 2. Configurer les profils
curl -X POST http://localhost:8080/api/profil/bio \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "alice", "bio": "Passionnée de randonnée"}'

curl -X POST http://localhost:8080/api/profil/orientation \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "alice", "orientation": "femmes"}'

curl -X POST http://localhost:8080/api/profil/bio \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "bob", "bio": "Amateur de cinéma"}'

curl -X POST http://localhost:8080/api/profil/orientation \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "bob", "orientation": "hommes"}'

curl -X POST http://localhost:8080/api/profil/bio \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "claire", "bio": "Chef cuisinière"}'

curl -X POST http://localhost:8080/api/profil/orientation \
  -H "Content-Type: application/json" \
  -d '{"nomUtilisateur": "claire", "orientation": "femmes"}'

# 3. Rechercher les profils intéressés par les femmes
curl -X GET http://localhost:8080/api/profils/femmes
```



