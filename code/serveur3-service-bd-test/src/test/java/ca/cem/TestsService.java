package ca.cem;

import ca.cem.exceptions.PasBonneChoseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = ServerApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestsService {

    @Autowired TrucService service;

    @Test
    public void testAjouterTrucOk() throws PasBonneChoseException {
        service.ajouterUnTruc("salut");
        // valider si "salut" est dans la liste
        assertTrue( service.listerTrucs().stream().anyMatch(t -> t.chose.equals("salut")));
    }

    @Test
    public void testAjouterTrucDeuxTrucsMemeChose() throws PasBonneChoseException {
        assertThrows(PasBonneChoseException.class, () -> {
            service.ajouterUnTruc("salut");
            service.ajouterUnTruc("salut");
        });
    }
}
