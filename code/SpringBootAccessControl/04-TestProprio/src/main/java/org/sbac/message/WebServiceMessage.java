package org.sbac.message;

import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// SÉCURISÉ : Identité du SecurityContext + vérification de propriété sur chaque message
@Controller
public class WebServiceMessage {

    @Autowired
    private ServiceMessage service;

    @PostMapping("/api/messages")
    public @ResponseBody RepMessage envoyer(@RequestBody ReqEnvoyerMessage req) {
        String utilisateurConnecte = utilisateurActuel();
        System.out.println("Message : envoi de " + utilisateurConnecte + " à " + req.destinataire);
        return service.envoyer(utilisateurConnecte, req.destinataire, req.contenu);
    }

    // SÉCURISÉ : vérifie que l'utilisateur connecté est expéditeur ou destinataire
    @GetMapping("/api/messages/{id}")
    public @ResponseBody RepMessage obtenirParId(@PathVariable Long id) {
        String utilisateurConnecte = utilisateurActuel();
        System.out.println("Message : lecture du message " + id + " par " + utilisateurConnecte);
        if (!service.estParticipant(id, utilisateurConnecte)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accès refusé");
        }
        return service.obtenirParId(id);
    }

    @GetMapping("/api/messages")
    public @ResponseBody List<RepMessage> listerMessages() {
        String utilisateurConnecte = utilisateurActuel();
        System.out.println("Message : liste des messages de " + utilisateurConnecte);
        return service.listerMessages(utilisateurConnecte);
    }

    private String utilisateurActuel() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ud.getUsername();
    }
}
