package org.sbac.id;

import org.sbac.model.*;
import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ServiceProfil {

    @Autowired          DepotUtilisateur depotUtilisateur;
    
    public RepProfil obtenirProfil(String nomUtilisateur) {
        MUtilisateur u = depotUtilisateur.findByNomUtilisateur(nomUtilisateur).get();
        return convertirEnRepProfil(u);
    }

    public void modifierBio(ReqModifierBio req, MUtilisateur user) {
        user.bio = req.bio;
        depotUtilisateur.save(user);
    }

    public void modifierOrientation(ReqModifierOrientation req, MUtilisateur user) {
        user.orientation = req.orientation;
        depotUtilisateur.save(user);
    }

    public List<RepProfil> listerParOrientation(String orientation) {
        Iterable<MUtilisateur> utilisateurs = depotUtilisateur.findAll();
        List<RepProfil> profils = new ArrayList<>();
        for (MUtilisateur u : utilisateurs) {
            if (orientation.equalsIgnoreCase(u.orientation)) {
                profils.add(convertirEnRepProfil(u));
            }
        }
        return profils;
    }

    public MUtilisateur utilisateurParSonNom(String nom) {
        return depotUtilisateur.findByNomUtilisateur(nom).get();
    }

    private RepProfil convertirEnRepProfil(MUtilisateur u) {
        RepProfil rep = new RepProfil();
        rep.id = u.id;
        rep.nomUtilisateur = u.nomUtilisateur;
        rep.bio = u.bio;
        rep.orientation = u.orientation;
        return rep;
    }

}
