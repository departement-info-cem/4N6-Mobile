package org.sbac.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepotUtilisateur extends CrudRepository<MUtilisateur, Long> {

    Optional<MUtilisateur> findByNomUtilisateur(String nomUtilisateur);
}
