/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temtipuse file, choose Tools | Temtipuses
 * and open the temtipuse in the editor.
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.ConnectorHB;
import practica1.TipusDeMenjar;

/**
 *
 * @author dantel
 */
public class tipusMenjarCRUD {
    private Session sesion; 
    private Transaction tx;  

    public int saveTipusDeMenjar(TipusDeMenjar tipus) throws HibernateException { 
        int id = 0;  
        try { 
            init(); 
            id = (int) sesion.save(tipus); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }  
        return id; 
    }  

    public void updateTipusDeMenjar(TipusDeMenjar tipus) throws HibernateException { 
        try{ 
            init(); 
            sesion.update(tipus); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
    }  

    public void deleteTipusDeMenjar(TipusDeMenjar tipus) throws HibernateException { 
        try { 
            init(); 
            sesion.delete(tipus); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally{ 
            sesion.close(); 
        } 
    }  

    public TipusDeMenjar getTipusDeMenjar(int idTipusDeMenjar) throws HibernateException { 
        TipusDeMenjar tipus = null;  
        try{ 
            init(); 
            tipus = (TipusDeMenjar) sesion.get(TipusDeMenjar.class, idTipusDeMenjar); 
        } finally { 
            sesion.close(); 
        }  
        return tipus; 
    }  

    public List<TipusDeMenjar> getListTipusDeMenjar() throws HibernateException { 
        List<TipusDeMenjar> listTipusDeMenjar = null;  
        try { 
            init(); 
            listTipusDeMenjar = sesion.createQuery("from tipus").list(); 
        } finally { 
            sesion.close(); 
        }  
        return listTipusDeMenjar; 
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
