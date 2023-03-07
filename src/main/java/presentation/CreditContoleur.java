package presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metier.ICreditMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Controller
public class CreditContoleur implements IHM{
    @Autowired
    @Qualifier("metier")
    ICreditMetier service;

    @Override
    public void Afficher_mensualité(Long idCredit) throws Exception {
          var credit = service.calculer_Mensualite(idCredit);
          System.out.println(credit);

    }
}
