package org.sbac.message;

import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// VULNÉRABILITÉ IDOR : L'identité vient du SecurityContext (correct),
// mais GET /api/messages/{id} ne vérifie pas que l'utilisateur est participant
@Controller
public class WebServiceMessage {

    @Autowired
    private ServiceMessage service;

    // L'expéditeur est forcé à l'utilisateur connecté (correct)
    @PostMapping("/api/messages")
    public @ResponseBody RepMessage envoyer(@RequestBody ReqEnvoyerMessage req) {
        String utilisateurConnecte = utilisateurActuel();
        System.out.println("Message : envoi de " + utilisateurConnecte + " à " + req.destinataire);
        return service.envoyer(utilisateurConnecte, req.destinataire, req.contenu);
    }

    // VULNÉRABILITÉ : retourne n'importe quel message par ID sans vérifier la propriété
    @GetMapping("/api/messages/{id}")
    public @ResponseBody RepMessage obtenirParId(@PathVariable Long id) {
        System.out.println("Message : lecture du message " + id);
        return service.obtenirParId(id);
    }

    // Utilise l'identité du SecurityContext (correct, ignore le paramètre)
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
