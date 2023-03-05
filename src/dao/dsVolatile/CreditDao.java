package dao.dsVolatile;
import dao.IDao;
import modele.Credit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreditDao implements IDao<Credit,Long> {

    @Override
    public Credit trouverParId(Long idCredit){
        System.out.println("[DAO -Ds Volatile]  trouver le Credit N : "+idCredit);
       return BD_Credit()
                   .stream()
                   .filter(Credit -> Credit.getID_ELRHAZI_ANASS() == idCredit)
                   .findFirst()
                   .orElse(null);
    }

    static Set<Credit> BD_Credit(){
        return new HashSet<Credit>(
                Arrays.asList(
                      new Credit(1L,20000.00,30,4.5,"Yassine",0.0),
                        new Credit(2L,250000.00,100,7.5,"ZZ",0.0),
                        new Credit(3L,1050000.00,300,10.5,"YY",0.0),
                        new Credit(4L,2000.00,12,3.0,"BB",0.0)
                )
        );
    }
}
