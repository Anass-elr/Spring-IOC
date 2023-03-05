package metier;

import dao.IDao;
import dao.dsVolatile.CreditDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import modele.Credit;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditMetier implements ICreditMetier{

   // IDao<Credit,Long> dao;
    IDao<Credit,Long> Creditdao;
    @Override
    public Credit calculer_Mensualite(Long idCredit) throws Exception{
        var credit = Creditdao.trouverParId(idCredit);

        if(credit == null) throw new Exception("Credit non trouver");
        else{
            double cp= credit.getCapitale_Emprunté();
            int nb = credit.getNombre_Mois();
            double t = credit.getTaux_Mensuel()/1200;

            double m = cp*t/(1-(Math.pow((1+t),-1*nb)));
              m= Math.round(m*100)/100;
              credit.setMensualité(m);
            return credit;
        }

    }
}
