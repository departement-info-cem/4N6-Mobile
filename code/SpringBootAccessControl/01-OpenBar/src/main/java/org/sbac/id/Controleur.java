package org.sbac.id;

import org.sbac.model.MUtilisateur;
import org.sbac.transfert.ReqConnexion;
import org.sbac.transfert.RepProfil;
import org.sbac.transfert.ReqModifierBio;
import org.sbac.transfert.ReqModifierOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controleur {

    @Autowired      private Service service;
    @Autowired      private ServiceProfil serviceProfil;

    @PostMapping("/api/id/signin")
    public @ResponseBody String signin(@RequestBody ReqConnexion s) throws Service.BadCredentials {
        System.out.println("ID : demande de connexion " + s);
        s.nomUtilisateur = s.nomUtilisateur.trim().toLowerCase();
        service.connexion(s.nomUtilisateur, s.motDePasse);
        return s.nomUtilisateur;
    }

    @PostMapping("/api/id/signup")
    public @ResponseBody String signup(@RequestBody ReqConnexion s) throws Service.BadCredentials {
        System.out.println("ID : demande inscription " + s);
        service.sinscrire(s);
        return signin(s);
    }

    @GetMapping("/api/profile/{nomUtilisateur}")
    public @ResponseBody RepProfil profile(@PathVariable String nomUtilisateur) {
        System.out.println("ID : demande de profil pour " + nomUtilisateur);
        return serviceProfil.obtenirProfil(nomUtilisateur);
    }

    @PostMapping("/api/profil/bio")
    public @ResponseBody String modifierBio(@RequestBody ReqModifierBio req) {
        System.out.println("Profil : modifier bio");
        serviceProfil.modifierBio(req, utilisateurActuel(req.nomUtilisateur));
        return "";
    }

    @PostMapping("/api/profil/orientation")
    public @ResponseBody String modifierOrientation(@RequestBody ReqModifierOrientation req) {
        System.out.println("Profil : modifier orientation");
        serviceProfil.modifierOrientation(req, utilisateurActuel(req.nomUtilisateur));
        return "";
    }

    @GetMapping("/api/profils/{orientation}")
    public @ResponseBody List<RepProfil> listerParOrientation(@PathVariable String orientation) {
        System.out.println("Profils : lister par orientation " + orientation);
        return serviceProfil.listerParOrientation(orientation);
    }

    @GetMapping("/api/profil/{nomUtilisateur}")
    public @ResponseBody RepProfil obtenirProfil(@PathVariable String nomUtilisateur) {
        System.out.println("Profil : obtenir pour " + nomUtilisateur);
        return serviceProfil.obtenirProfil(nomUtilisateur);
    }

    private MUtilisateur utilisateurActuel(String nomUtilisateur) {
        MUtilisateur utilisateur = serviceProfil.utilisateurParSonNom( nomUtilisateur );
        return utilisateur;
    }

}
