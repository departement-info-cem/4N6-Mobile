package org.dupont.testget;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonControleur {

    // on testera avec une requête http://localhost:8080/test/99/test?id=2
    // ou http://localhost:8080/test/77/test
    @GetMapping(value = "/test/{coucou}/test")
    public @ResponseBody String signout(@PathVariable Long coucou, @RequestParam String id) {
        System.out.println("Requête reçue : " + coucou + " " + id);
        return (coucou / 2) + "";
    }

    @GetMapping(value = "/bonjour")
    public @ResponseBody String bonjour() {
        System.out.println("Requête reçue sur bonjour ");
        return "bonjour le monde";
    }

}
