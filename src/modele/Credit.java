package modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class Credit {
   private  Long ID_ELRHAZI_ANASS;
   private  Double capitale_Emprunté;
   private  Integer nombre_Mois;

   private  Double taux_Mensuel;
   private  String demendeur;
   private  Double mensualité;


   public String toString(){
      var st="===================================================    \n";
          st+="=> Credit N : "+getID_ELRHAZI_ANASS()+               "\n";
          st+="=> Nom Demendeur Credit  : "+getDemendeur()+         "\n";
          st+="----------------------------------------------        \n";
          st+="=> Capitale Emprunté  : "+getCapitale_Emprunté()+    "\n";

          st+="=> Nombre de mois : "+getNombre_Mois()+             "\n";
          st+="=> Taux Mensuel : "+getTaux_Mensuel()+               "\n";
          st+="=> Mensualité  : "
                  +(getNombre_Mois() == 0.0 ? "NON CALCULE" : getMensualité() +"Dh/Mois")+
                  "\n";
         st+="===================================================    \n";

         return st;
   }


}
