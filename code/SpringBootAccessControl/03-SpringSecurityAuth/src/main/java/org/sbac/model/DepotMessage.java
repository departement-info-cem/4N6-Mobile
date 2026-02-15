package org.sbac.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotMessage extends CrudRepository<MMessage, Long> {

    List<MMessage> findByExpediteurNomUtilisateurOrDestinataireNomUtilisateur(
            String expediteur, String destinataire);
}
