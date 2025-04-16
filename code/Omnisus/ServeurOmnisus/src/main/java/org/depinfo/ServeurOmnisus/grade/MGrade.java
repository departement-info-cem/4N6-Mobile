package org.depinfo.ServeurOmnisus.grade;

import jakarta.persistence.*;
import org.depinfo.ServeurOmnisus.user.MUser;
import org.joda.time.DateTime;

@Entity
public class MGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Integer grade;
    public String name;
    public DateTime editDate;

    @ManyToOne(fetch= FetchType.EAGER)
    public MUser user;
}
