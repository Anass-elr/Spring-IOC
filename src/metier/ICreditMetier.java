package metier;

import modele.Credit;

public interface ICreditMetier {

    Credit calculer_mensualite(Long idCredit);
}
