package ca.cem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Sera automatiquement détecté au démarrage du serveur
@Controller
public class Controlleur {

	@GetMapping("/fibo/{a}/{b}")
	public @ResponseBody List<String> fibo(
			@PathVariable long a,
			@PathVariable long b)  {
		List<String> res = new ArrayList<>();
		for (long i = a ; i <= b ; i++) {
			res.add(fibo(i)+"");
		}
		return res;
	}

	Long fibo(long n) {
		if (n == 0) return 0L;
		if (n == 1) return 1L;
		long[] res = new long[(int) n+1];
		for(int i = 0 ; i <= n ; i++) {
			if (i == 0) res[i] = 0;
			if (i == 1) res[i] = 1;
			if (i > 1) res[i] = res[i-1] + res[i-2];
		}
		return res[(int) n];
	}
}
