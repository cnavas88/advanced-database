package CRUD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.Chef;
import practica1.ConnectorHB;

/**
 *
 * @author dantel
 */
public class ChefCRUD {
    private Session sesion; 
    private Transaction tx;  

    public int saveChef(Chef chef) throws HibernateException { 
        int id = 0;  
        try { 
            init(); 
            id = (int) sesion.save(chef); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }  
        return id; 
    }  

    public void updateChef(Chef chef) throws HibernateException { 
        try{ 
            init(); 
            sesion.update(chef); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
    }  

    public void deleteChef(Chef chef) throws HibernateException { 
        try { 
            init(); 
            sesion.delete(chef); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally{ 
            sesion.close(); 
        } 
    }  

    public Chef getChef(int idChef) throws HibernateException { 
        Chef chef = null;  
        try{ 
            init(); 
            chef = (Chef) sesion.get(Chef.class, idChef); 
        } finally { 
            sesion.close(); 
        }  
        return chef; 
    }  

    public List<Chef> getListChef() throws HibernateException { 
        List<Chef> listChef = null;  
        try { 
            init(); 
            listChef = sesion.createQuery("from Chef").list(); 
        } finally { 
            sesion.close(); 
        }  
        return listChef; 
    }  

    private void init() throws HibernateException { 
        sesion = ConnectorHB.getSession();
        tx = sesion.beginTransaction(); 
    }  

    private void manejaExcepcion(HibernateException he) throws HibernateException { 
        tx.rollback(); 
        throw new HibernateException("Ocurrio un error al intentar accceder a los datos", he); 
    } 
}
