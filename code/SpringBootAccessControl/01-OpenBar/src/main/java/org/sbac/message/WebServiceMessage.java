package org.sbac.message;

import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// VULNÉRABILITÉ : Aucun contrôle d'accès — tout est public
@Controller
public class WebServiceMessage {

    @Autowired
    private ServiceMessage service;

    @PostMapping("/api/messages")
    public @ResponseBody RepMessage envoyer(@RequestBody ReqEnvoyerMessage req) {
        System.out.println("Message : envoi de " + req.expediteur + " à " + req.destinataire);
        return service.envoyer(req);
    }

    @GetMapping("/api/messages/{id}")
    public @ResponseBody RepMessage obtenirParId(@PathVariable Long id) {
        System.out.println("Message : lecture du message " + id);
        return service.obtenirParId(id);
    }

    @GetMapping("/api/messages")
    public @ResponseBody List<RepMessage> listerMessages(@RequestParam String utilisateur) {
        System.out.println("Message : liste des messages de " + utilisateur);
        return service.listerMessages(utilisateur);
    }
}
