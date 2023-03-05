package metier;

import modele.Credit;

public interface ICreditMetier {


    Credit calculer_Mensualite(Long idCredit) throws Exception;
}
