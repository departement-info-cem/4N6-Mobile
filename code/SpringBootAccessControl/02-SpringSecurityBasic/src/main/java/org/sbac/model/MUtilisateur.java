package org.sbac.model;

import jakarta.persistence.*;

@Entity
public class MUtilisateur {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     public Long id;
    @Basic  public String nomUtilisateur;
    @Basic  public String motDePasse;
    @Basic  public String bio;
    @Basic  public String orientation;

}
