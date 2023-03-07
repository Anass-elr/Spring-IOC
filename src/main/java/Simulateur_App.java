import dao.IDao;
import dao.dsVolatile.CreditDao;
import metier.CreditMetier;
import metier.ICreditMetier;
import modele.Credit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import presentation.CreditContoleur;
import presentation.IHM;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
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


     static IHM creditControleur;


    static void test2() throws Exception {
        String daoClass;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");

        if(propertiesFile == null) throw new Exception("Fichier Config Introuvable !!");
        else {
            try{
                properties.load(propertiesFile);
                daoClass = properties.getProperty("DAO");
                serviceClass = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            }
            catch (IOException e){
                throw new Exception("PRB DE CHARGEMENT Des proprietés du fichier config");
            }
            finally {
                properties.clear();
            }
        }
        try{
            Class cDao = Class.forName(daoClass);
            Class cMetier = Class.forName(serviceClass);
            Class cCont=Class.forName(controllerClass);

            var dao = (IDao<Credit,Long>)cDao.getDeclaredConstructor().newInstance();
            var metier = (ICreditMetier)cMetier.getDeclaredConstructor().newInstance();
            creditControleur = (IHM)cCont.getDeclaredConstructor().newInstance();

            Method setDao = cMetier.getMethod("setCreditdao",IDao.class);
            setDao.invoke(metier,dao);

            Method setMetier = cCont.getMethod("setService",ICreditMetier.class);
            setMetier.invoke(creditControleur,metier);

            creditControleur.Afficher_mensualité(3L);
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    static void test3() throws Exception {
         ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");

         creditControleur = (IHM) context.getBean("controller");
         creditControleur.Afficher_mensualité(1L);

    }


    static void test4() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext("dao","metier","presentation");

        creditControleur = (IHM)context.getBean(IHM.class);
        creditControleur.Afficher_mensualité(2L);

    }


    public static void main(String[] args) throws Exception {
        // Couplage Faible
          test4();
    }

}
