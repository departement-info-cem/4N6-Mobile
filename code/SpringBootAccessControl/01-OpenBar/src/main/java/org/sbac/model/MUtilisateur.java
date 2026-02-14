package org.sbac.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MUtilisateur {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id     public Long id;
    @Basic  public String nomUtilisateur;
    @Basic  public String motDePasse;
    @Basic  public String bio;
    @Basic  public String orientation;

}
