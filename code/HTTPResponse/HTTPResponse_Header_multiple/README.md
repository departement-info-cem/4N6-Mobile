# Réponses réussies

## 200 OK
La requête a réussi. La signification du succès dépend de la méthode HTTP :  
GET : La ressource a été récupérée et est transmise dans le corps du message.  
HEAD : Les en-têtes de l'entité sont dans le corps du message.  
PUT ou POST : La ressource décrivant le résultat de l'action est transmise dans le corps du message.  
TRACE : Le corps du message contient le message de requête tel qu'il a été reçu par le serveur.

## 201 Créé
La requête a réussi et une nouvelle ressource a été créée en conséquence. C'est généralement la réponse envoyée après des requêtes POST, ou certaines requêtes PUT.

## 202 Accepté
La requête a été reçue mais n'a pas encore été traitée. Elle est non engageante, car il n'y a pas de moyen en HTTP pour envoyer ultérieurement une réponse asynchrone indiquant le résultat de la requête. Elle est destinée aux cas où un autre processus ou serveur gère la requête, ou pour un traitement par lot.

# Messages de redirection
## 300 Choix multiple
La requête a plus d'une réponse possible. L'agent utilisateur ou l'utilisateur doit en choisir une. (Il n'existe pas de manière standardisée de choisir l'une des réponses, mais il est recommandé d'utiliser des liens HTML vers les possibilités pour que l'utilisateur puisse choisir.)

## 301 Déplacé de façon permanente
L'URL de la ressource demandée a été changée de façon permanente. La nouvelle URL est donnée dans la réponse.

## 302 Trouvé
Ce code de réponse signifie que l'URI de la ressource demandée a été temporairement changée. Des changements ultérieurs dans l'URI pourraient être effectués à l'avenir. Par conséquent, cette même URI doit être utilisée par le client dans les futures requêtes.

## 307 Redirection temporaire
Le serveur envoie cette réponse pour diriger le client vers la ressource demandée à une autre URI avec la même méthode qui a été utilisée dans la requête précédente. Cela a la même sémantique que le code de réponse HTTP 302 Found, à l'exception que l'agent utilisateur ne doit pas changer la méthode HTTP utilisée : si un POST a été utilisé dans la première requête, un POST doit être utilisé dans la deuxième requête.

## 308 Redirection permanente
Cela signifie que la ressource est désormais située de manière permanente à une autre URI, spécifiée par l'en-tête de réponse HTTP Location:. Cela a la même sémantique que le code de réponse HTTP 301 Déplacé de façon permanente, à l'exception que l'agent utilisateur ne doit pas changer la méthode HTTP utilisée : si un POST a été utilisé dans la première requête, un POST doit être utilisé dans la deuxième requête.

# Réponses d'erreur client
## 400 Requête incorrecte
Le serveur n'a pas pu comprendre la requête en raison d'une syntaxe invalide.

## 401 Non autorisé
Bien que la norme HTTP spécifie "non autorisé", sémantiquement cette réponse signifie "non authentifié". Autrement dit, le client doit s'authentifier pour obtenir la réponse demandée.

## 402 Paiement requis
Ce code de réponse est réservé à une utilisation future. L'objectif initial de la création de ce code était de l'utiliser pour les systèmes de paiement numériques, cependant ce code d'état est utilisé très rarement et aucune convention standard n'existe.

## 403 Interdit
Le client n'a pas les droits d'accès au contenu ; c'est-à-dire qu'il n'est pas autorisé, donc le serveur refuse de fournir la ressource demandée. Contrairement à 401, l'identité du client est connue du serveur.

## 404 Non trouvé
Le serveur ne peut pas trouver la ressource demandée. Dans le navigateur, cela signifie que l'URL n'est pas reconnue. Dans une API, cela peut également signifier que le point de terminaison est valide mais que la ressource elle-même n'existe pas. Les serveurs peuvent également renvoyer cette réponse au lieu de 403 pour masquer l'existence d'une ressource à un client non autorisé. Ce code de réponse est probablement le plus célèbre en raison de sa fréquence sur le web.

## 405 Méthode non autorisée
La méthode de requête est connue du serveur mais a été désactivée et ne peut pas être utilisée. Par exemple, une API peut interdire la suppression d'une ressource. Les deux méthodes obligatoires, GET et HEAD, ne doivent jamais être désactivées et ne doivent pas renvoyer ce code d'erreur.

## 406 Non acceptable
Cette réponse est envoyée lorsque le serveur web, après avoir effectué une négociation de contenu pilotée par le serveur, ne trouve aucun contenu qui corresponde aux critères donnés par l'agent utilisateur.

## 408 Délai d'attente de la requête
Cette réponse est envoyée sur une connexion inactive par certains serveurs, même sans aucune requête précédente du client. Cela signifie que le serveur aimerait fermer cette connexion inutilisée. Cette réponse est utilisée beaucoup plus depuis que certains navigateurs, comme Chrome, Firefox 27+, ou IE9, utilisent des mécanismes de pré-connexion HTTP pour accélérer la navigation. Notez également que certains serveurs se contentent de fermer la connexion sans envoyer ce message.

## 415 Type de support non pris en charge
Le format média des données demandées n'est pas pris en charge par le serveur, donc le serveur rejette la requête.

## 429 Trop de requêtes
L'utilisateur a envoyé trop de requêtes dans un laps de temps donné ("limitation du taux").

# Réponses d'erreur serveur
## 500 Erreur interne du serveur
Le serveur a rencontré une situation qu'il ne sait pas gérer.

## 501 Non implémenté
La méthode de requête n'est pas prise en charge par le serveur et ne peut pas être traitée. Les seules méthodes que les serveurs sont tenus de prendre en charge (et donc qui ne doivent pas renvoyer ce code) sont GET et HEAD.

## 502 Mauvaise passerelle
Cette réponse d'erreur signifie que le serveur, tout en agissant comme une passerelle pour obtenir une réponse nécessaire pour traiter la requête, a reçu une réponse invalide.

## 503 Service non disponible
Le serveur n'est pas prêt à traiter la requête. Les causes courantes sont un serveur en maintenance ou surchargé. Notez qu'avec cette réponse, une page conviviale expliquant le problème doit être envoyée. Ces réponses doivent être utilisées pour des conditions temporaires et l'en-tête HTTP Retry-After: doit, si possible, contenir le temps estimé avant la reprise du service. Le webmaster doit également prendre soin des en-têtes liés au cache qui sont envoyés avec cette réponse, car ces réponses de condition temporaire ne doivent généralement pas être mises en cache.

## 505 Version HTTP non supportée
La version HTTP utilisée dans la requête n'est pas prise en charge par le serveur.