package ca.cem;

import ca.cem.exceptions.PasBonneChoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Sera automatiquement détecté au démarrage du serveur
@Controller
public class Controlleur {

	@Autowired
	public TrucService trucService;

	@GetMapping("/trucs")
	public @ResponseBody List<Truc> trucs()  {
		return trucService.listerTrucs();
	}

	@GetMapping("/ajouterTruc/{chose}")
	public @ResponseBody String ajouterEnGet(
			@PathVariable String chose) throws PasBonneChoseException {
		trucService.ajouterUnTruc(chose);
		return "ok";
	}

	@PostMapping("/ajouterTrucPost")
	public @ResponseBody String ajouterEnPost(
			@RequestBody String chose) throws PasBonneChoseException {
		trucService.ajouterUnTruc(chose);
		return "ok";
	}

	@PutMapping("/ajouterTrucPut")
	public @ResponseBody String ajouterEnPut(
			@RequestBody String chose) throws PasBonneChoseException {
		trucService.ajouterUnTruc(chose);
		return "ok";
	}

}
