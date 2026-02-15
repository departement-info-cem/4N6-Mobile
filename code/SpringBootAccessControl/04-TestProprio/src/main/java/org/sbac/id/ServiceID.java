package org.sbac.id;

import org.sbac.model.MUtilisateur;
import org.sbac.model.DepotUtilisateur;

import org.sbac.transfert.ReqInscription;
import org.sbac.transfert.RepProfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Component
@Transactional
public class ServiceID implements UserDetailsService {

    public static class BadCredentials extends Exception { }

    @Autowired @Lazy private PasswordEncoder passwordEncoder;
    @Autowired private DepotUtilisateur userRepository;

    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        MUtilisateur utilisateur = userRepository.findByNomUtilisateur(nom.trim().toLowerCase()).get();
        User u = new User(utilisateur.nomUtilisateur, utilisateur.motDePasse, new ArrayList<>());
        return u;
    }

    public RepProfil profil(String nomUtilisateur) {
        MUtilisateur utilisateur = userRepository.findByNomUtilisateur(nomUtilisateur).get();
        RepProfil rep = new RepProfil();
        rep.id = utilisateur.id;
        rep.nomUtilisateur = utilisateur.nomUtilisateur;
        rep.bio = utilisateur.bio;
        rep.orientation = utilisateur.orientation;
        return rep;
    }

    public void sinscrire(ReqInscription req) throws BadCredentials {
        String nom = req.nomUtilisateur.toLowerCase().trim();
        try{
            userRepository.findByNomUtilisateur(nom).get();
            throw new BadCredentials();
        } catch (NoSuchElementException e) {
            MUtilisateur p = new MUtilisateur();
            p.nomUtilisateur = nom;
            p.motDePasse = passwordEncoder.encode(req.motDePasse);
            userRepository.save(p);
        }
    }
}
