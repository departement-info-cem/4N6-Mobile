package org.sbac.id;

import org.sbac.model.MUtilisateur;
import org.sbac.profil.ServiceProfil;
import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controleur {

    @Autowired      private AuthenticationManager authManager;
    @Autowired      private ServiceID userService;
    @Autowired
    private ServiceProfil serviceProfil;

    @PostMapping("/api/id/signin")
    public @ResponseBody String signin(@RequestBody ReqConnexion s) throws ServiceID.BadCredentials {
        System.out.println("ID : demande de connexion " + s);
        s.nomUtilisateur = s.nomUtilisateur.trim().toLowerCase();
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(s.nomUtilisateur, s.motDePasse);
            authManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("Logged as " + s.nomUtilisateur);
            return s.nomUtilisateur;
        } catch (BadCredentialsException bce) {
            throw new ServiceID.BadCredentials();
        }
    }

    @PostMapping("/api/id/signup")
    public @ResponseBody RepProfil signup(@RequestBody ReqConnexion s) throws ServiceID.BadCredentials {
        System.out.println("ID : demande inscription " + s);
        RepProfil profil = userService.sinscrire(s);
        signin(s);
        return profil;
    }

    @PostMapping("/api/profil/bio")
    public @ResponseBody String modifierBio(@RequestBody ReqModifierBio req) {
        System.out.println("Profil : modifier bio");
        serviceProfil.modifierBio(req, utilisateurActuel());
        return "";
    }

    @PostMapping("/api/profil/orientation")
    public @ResponseBody String modifierOrientation(@RequestBody ReqModifierOrientation req) {
        System.out.println("Profil : modifier orientation");
        serviceProfil.modifierOrientation(req, utilisateurActuel());
        return "";
    }

    @GetMapping("/api/profils/{orientation}")
    public @ResponseBody RepListeProfils listerParOrientation(@PathVariable String orientation) {
        System.out.println("Profil : lister par orientation " + orientation);
        RepListeProfils response = new RepListeProfils();
        response.profils = serviceProfil.listerParOrientation(orientation);
        return response;
    }

    @GetMapping("/api/profil/{nomUtilisateur}")
    public @ResponseBody RepProfil obtenirProfil(@PathVariable String nomUtilisateur) {
        System.out.println("Profil : obtenir profil " + nomUtilisateur);
        return serviceProfil.obtenirProfil(nomUtilisateur);
    }

    private MUtilisateur utilisateurActuel() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MUtilisateur utilisateur = serviceProfil.utilisateurParSonNom(ud.getUsername());
        return utilisateur;
    }

}
