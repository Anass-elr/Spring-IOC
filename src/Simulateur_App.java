import dao.IDao;
import dao.dsVolatile.CreditDao;
import metier.CreditMetier;
import metier.ICreditMetier;
import presentation.CreditContoleur;

import java.util.Scanner;

public class Simulateur_App {

    static Scanner  clavier = new Scanner(System.in);

    private static boolean estUnNombre(String input){
        try {
            Integer.parseInt(input);return true;
        }
        catch(Exception e){
            return false;
        }

    }

     public static void test1(){
         // Instanciation des différents Composants de l'application

         var dao = new CreditDao();
         var metier = new CreditMetier();
         var controller = new CreditContoleur();

        // Injection des dependance
         metier.setCreditdao(dao);
         controller.setService(metier);

         //tester
         String rep= "";
         do{
             System.out.println("=> [Test 1] Calcule de Mensualité de Credit <= \n");
              try{
                  String input= "";
                    while(true){
                        System.out.println("=> Entrez ID du Credit  :  \n");
                        input = clavier.nextLine();
                        if(estUnNombre(input)) break;
                        System.err.println("Entrée non Valide !!");
                    }
                    long id= Long.parseLong(input);
                    controller.Afficher_mensualité(id);
              }catch(Exception e){
                  System.err.println(e.getMessage());
              }
             System.out.println("Voulez Vous Quittez (Oui/Non) ?  \n");
              rep=clavier.nextLine();

         }while(!rep.equalsIgnoreCase("oui"));
         System.out.println("=> Au revoir");

     }


    public static void main(String[] args) {
        test1();
    }

}
