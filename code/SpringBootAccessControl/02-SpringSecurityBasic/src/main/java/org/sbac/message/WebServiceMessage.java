package org.sbac.message;

import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// VULNÉRABILITÉ : L'identité est prise du paramètre de la requête,
// pas du SecurityContext (JSESSIONID ignoré pour l'identité)
@Controller
public class WebServiceMessage {

    @Autowired
    private ServiceMessage service;

    // L'expéditeur vient du body — on peut envoyer en se faisant passer pour quelqu'un d'autre
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

    // Le paramètre 'utilisateur' vient de l'URL — on peut lire la boîte de réception de n'importe qui
    @GetMapping("/api/messages")
    public @ResponseBody List<RepMessage> listerMessages(@RequestParam String utilisateur) {
        System.out.println("Message : liste des messages de " + utilisateur);
        return service.listerMessages(utilisateur);
    }
}
