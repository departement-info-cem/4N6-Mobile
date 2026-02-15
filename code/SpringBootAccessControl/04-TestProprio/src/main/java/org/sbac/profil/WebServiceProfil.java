package org.sbac.profil;

import org.sbac.model.MUtilisateur;

import org.sbac.transfert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebServiceProfil {

	@Autowired
	private ServiceProfil service;

	@PostMapping("/api/profil/bio")
	public @ResponseBody String modifierBio(@RequestBody ReqModifierBio req) {
		System.out.println("Profil : modifier bio");
		service.modifierBio(req, utilisateurActuel());
		return "";
	}

	@PostMapping("/api/profil/orientation")
	public @ResponseBody String modifierOrientation(@RequestBody ReqModifierOrientation req) {
		System.out.println("Profil : modifier orientation");
		service.modifierOrientation(req, utilisateurActuel());
		return "";
	}

	@GetMapping("/api/profils/{orientation}")
	public @ResponseBody List<RepProfil> listerParOrientation(@PathVariable String orientation) {
		System.out.println("Profils : lister par orientation " + orientation);
		return service.listerParOrientation(orientation);
	}

	@GetMapping("/api/profil/{nomUtilisateur}")
	public @ResponseBody RepProfil obtenirProfil(@PathVariable String nomUtilisateur) {
		System.out.println("Profil : obtenir pour " + nomUtilisateur);
		return service.obtenirProfil(nomUtilisateur);
	}

	private MUtilisateur utilisateurActuel() {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MUtilisateur utilisateur = service.utilisateurParSonNom(ud.getUsername());
		return utilisateur;
	}

}
