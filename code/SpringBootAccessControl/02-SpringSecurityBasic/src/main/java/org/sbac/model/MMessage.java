package org.sbac.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MMessage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     public Long id;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    public MUtilisateur expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    public MUtilisateur destinataire;

    @Basic  public String contenu;
    @Basic  public LocalDateTime dateEnvoi;

}
