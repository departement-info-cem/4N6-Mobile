package org.sbac.id;

import org.sbac.model.MUtilisateur;
import org.sbac.transfert.ReqConnexion;
import org.sbac.transfert.RepProfil;
import org.sbac.transfert.ReqModifierBio;
import org.sbac.transfert.ReqModifierOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Controleur {

    @Autowired      private AuthenticationManager authManager;
    @Autowired      private ServiceID userService;
    @Autowired      private ServiceProfil serviceProfil;
    @Autowired      private SecurityContextRepository securityContextRepository;

    @PostMapping("/api/id/signin")
    public @ResponseBody String signin(@RequestBody ReqConnexion s, HttpServletRequest request, HttpServletResponse response) throws ServiceID.BadCredentials {
        System.out.println("ID : demande de connexion " + s);
        s.nomUtilisateur = s.nomUtilisateur.trim().toLowerCase();
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(s.nomUtilisateur, s.motDePasse);
            Authentication authenticatedAuth = authManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticatedAuth);
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            System.out.println("Logged as " + s.nomUtilisateur);
            return s.nomUtilisateur;
        } catch (BadCredentialsException bce) {
            throw new ServiceID.BadCredentials();
        }
    }

    @PostMapping("/api/id/signup")
    public @ResponseBody String signup(@RequestBody ReqConnexion s, HttpServletRequest request, HttpServletResponse response) throws ServiceID.BadCredentials {
        System.out.println("ID : demande inscription " + s);
        userService.sinscrire(s);
        return signin(s, request, response);
    }

    @GetMapping("/api/profile/{nomUtilisateur}")
    public @ResponseBody RepProfil profil(@PathVariable String nomUtilisateur) {
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            String authenticatedUser = auth.getName();
            if (!authenticatedUser.equals(nomUtilisateur)) {
                throw new RuntimeException("Utilisateur non autorisé");
            }
        }
        MUtilisateur utilisateur = serviceProfil.utilisateurParSonNom(nomUtilisateur);
        return utilisateur;
    }

}
