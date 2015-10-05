/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.ConnectorHB;
import practica1.Plat;

/**
 *
 * @author dantel
 */
public class platCRUD {
    private Session sesion; 
    private Transaction tx;  

    public int savePlat(Plat plat) throws HibernateException { 
        int id = 0;  
        try { 
            init(); 
            id = (int) sesion.save(plat); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }  
        return id; 
    }  

    public void updatePlat(Plat plat) throws HibernateException { 
        try{ 
            init(); 
            sesion.update(plat); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
    }  

    public void deletePlat(Plat plat) throws HibernateException { 
        try { 
            init(); 
            sesion.delete(plat); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally{ 
            sesion.close(); 
        } 
    }  

    public Plat getPlat(int idPlat) throws HibernateException { 
        Plat plat = null;  
        try{ 
            init(); 
            plat = (Plat) sesion.get(Plat.class, idPlat); 
        } finally { 
            sesion.close(); 
        }  
        return plat; 
    }  

    public List<Plat> getListPlat() throws HibernateException { 
        List<Plat> listPlat = null;  
        try { 
            init(); 
            listPlat = sesion.createQuery("from plat").list(); 
        } finally { 
            sesion.close(); 
        }  
        return listPlat; 
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
