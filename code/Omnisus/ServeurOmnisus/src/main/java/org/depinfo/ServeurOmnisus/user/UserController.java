package org.depinfo.ServeurOmnisus.user;

import org.depinfo.ServeurOmnisus.user.dto.SigninRequest;
import org.depinfo.ServeurOmnisus.user.dto.SigninResponse;
import org.depinfo.ServeurOmnisus.user.exception.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserService userService;
    @PostMapping("/api/id/signin")
    public @ResponseBody SigninResponse signin(@RequestBody SigninRequest s) throws BadCredentialsException {
        s.username = s.username.trim().toLowerCase();
        try {
            Authentication requestAuth = new UsernamePasswordAuthenticationToken(s.username, s.password);
            Authentication resultAuth = authManager.authenticate(requestAuth);

            SecurityContextHolder.getContext().setAuthentication(resultAuth);

            SigninResponse resp = new SigninResponse();
            MUser loggedUser = userService.studentFromUsername(s.username);
            resp.id = loggedUser.id;
            resp.username = loggedUser.username;
            return resp;
        } catch (org.springframework.security.authentication.BadCredentialsException bce) {
            throw new BadCredentialsException();
        }
    }

    @PostMapping("/api/id/signout")
    public @ResponseBody String signout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Ok!";
    }

    @PutMapping("/api/student")
    public @ResponseBody String updateUser(@RequestBody String publicName) {
        MUser student = currentStudent();
        System.out.println(student.username + " : " + publicName);
        this.userService.updateUser(student.id, publicName);
        return "Ok!";
    }

    private MUser currentStudent() {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.studentFromUsername(ud.getUsername());
    }

}
