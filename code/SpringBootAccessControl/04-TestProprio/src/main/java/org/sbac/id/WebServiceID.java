package org.sbac.id;

import org.sbac.transfert.ReqConnexion;
import org.sbac.transfert.RepProfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebServiceID {

    @Autowired      private AuthenticationManager authManager;
    @Autowired      private ServiceID userService;
    @Autowired      private SecurityContextRepository securityContextRepository;

    @PostMapping("/api/id/signin")
    public @ResponseBody RepProfil signin(@RequestBody ReqConnexion s, HttpServletRequest request, HttpServletResponse response) throws ServiceID.BadCredentials {
        System.out.println("ID : demande de connexion " + s);
        s.nomUtilisateur = s.nomUtilisateur.trim().toLowerCase();
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(s.nomUtilisateur, s.motDePasse);
            Authentication authenticatedAuth = authManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticatedAuth);
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            System.out.println("Logged as " + s.nomUtilisateur);
            return userService.profil(s.nomUtilisateur);
        } catch (BadCredentialsException bce) {
            throw new ServiceID.BadCredentials();
        }
    }

    @PostMapping("/api/id/signup")
    public @ResponseBody RepProfil signup(@RequestBody ReqConnexion s, HttpServletRequest request, HttpServletResponse response) throws ServiceID.BadCredentials {
        System.out.println("ID : demande inscription " + s);
        userService.sinscrire(s);
        return signin(s, request, response);
    }

}
