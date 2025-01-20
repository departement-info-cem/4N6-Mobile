package ca.cem;

import ca.cem.exceptions.PasBonneChoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrucService {

    @Autowired
    private TrucRepo trucRepo;

    public void ajouterUnTruc(String leChoseDuTruc) throws PasBonneChoseException {
        Truc truc = new Truc();
        if (leChoseDuTruc == null) {
            throw new PasBonneChoseException();
        }
        Truc trucExistant = trucRepo.findByChose(leChoseDuTruc);
        if (trucExistant != null) {
            throw new PasBonneChoseException();
        }
        truc.chose = leChoseDuTruc;
        trucRepo.save(truc);
    }

    public List<Truc> listerTrucs() {
        return trucRepo.findAll();
    }

}
