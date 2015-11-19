/*
 * ConnectorHB.java
 */

package practica1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Esta Clase define el conector de java con Hibernate
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
@SuppressWarnings("deprecation")
public class ConnectorHB {
    // Atributos
    private static SessionFactory sf = null;
    
    // Constructor vacio
    public ConnectorHB() {

    }
    
    //Se instancia antes del constructor
    static{
        //Inicializa el SF buscando los ficheros de configuracion
        try {
            sf = new Configuration().configure().buildSessionFactory();
            System.out.println("Instanciando SF");
        } catch (HibernateException e) {
            System.out.println("Error SF: "+e.getMessage());
        }
    }
    
    /**
     * Metodo que devuelve la session a hibernate inicializada
     * @return la session hibernate
     */
    public static Session getSession(){
        Session session = sf.openSession();
        return session;
    }
}