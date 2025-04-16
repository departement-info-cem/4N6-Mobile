package org.depinfo.ServeurOmnisus.grade;

import org.depinfo.ServeurOmnisus.grade.dto.ScoreBoardResponse;
import org.depinfo.ServeurOmnisus.grade.dto.UserDetailsResponse;
import org.depinfo.ServeurOmnisus.user.MUser;

import java.util.List;

public interface GradeService {

    UserDetailsResponse detail(MUser student);

    List<ScoreBoardResponse> allDetails();
    void deleteAll();
}