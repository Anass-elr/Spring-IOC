package presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metier.CreditException;
import metier.CreditMetier;
import metier.ICreditMetier;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditContoleur implements IHM{

    ICreditMetier service;

    @Override
    public void Afficher_mensualité(Long idCredit) throws Exception {
          var credit = service.calculer_Mensualite(idCredit);
          System.out.println(credit);

    }
}
