package org.sbac.message;

import org.sbac.model.*;
import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ServiceMessage {

    @Autowired  DepotMessage depotMessage;
    @Autowired  DepotUtilisateur depotUtilisateur;

    public RepMessage envoyer(ReqEnvoyerMessage req) {
        MUtilisateur exp = depotUtilisateur.findByNomUtilisateur(req.expediteur).get();
        MUtilisateur dest = depotUtilisateur.findByNomUtilisateur(req.destinataire).get();
        MMessage m = new MMessage();
        m.expediteur = exp;
        m.destinataire = dest;
        m.contenu = req.contenu;
        m.dateEnvoi = LocalDateTime.now();
        depotMessage.save(m);
        return convertir(m);
    }

    public RepMessage obtenirParId(Long id) {
        MMessage m = depotMessage.findById(id).get();
        return convertir(m);
    }

    public List<RepMessage> listerMessages(String nomUtilisateur) {
        List<MMessage> messages = depotMessage
                .findByExpediteurNomUtilisateurOrDestinataireNomUtilisateur(
                        nomUtilisateur, nomUtilisateur);
        List<RepMessage> result = new ArrayList<>();
        for (MMessage m : messages) {
            result.add(convertir(m));
        }
        return result;
    }

    private RepMessage convertir(MMessage m) {
        RepMessage rep = new RepMessage();
        rep.id = m.id;
        rep.expediteur = m.expediteur.nomUtilisateur;
        rep.destinataire = m.destinataire.nomUtilisateur;
        rep.contenu = m.contenu;
        rep.dateEnvoi = m.dateEnvoi;
        return rep;
    }
}
