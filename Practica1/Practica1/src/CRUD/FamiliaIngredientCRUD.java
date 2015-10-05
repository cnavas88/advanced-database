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
import practica1.FamiliaIngredient;
import practica1.ConnectorHB;

/**
 *
 * @author dantel
 */
public class FamiliaIngredientCRUD {
    private Session sesion; 
    private Transaction tx;  

    public int saveFamiliaIngredient(FamiliaIngredient fam) throws HibernateException { 
        int id = 0;  
        try { 
            init(); 
            id = (int) sesion.save(fam); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }  
        return id; 
    }  

    public void updateFamiliaIngredient(FamiliaIngredient fam) throws HibernateException { 
        try{ 
            init(); 
            sesion.update(fam); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
    }  

    public void deleteFamiliaIngredient(FamiliaIngredient fam) throws HibernateException { 
        try { 
            init(); 
            sesion.delete(fam); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally{ 
            sesion.close(); 
        } 
    }  

    public FamiliaIngredient getFamiliaIngredient(int idFamiliaIngredient) throws HibernateException { 
        FamiliaIngredient fam = null;  
        try{ 
            init(); 
            fam = (FamiliaIngredient) sesion.get(FamiliaIngredient.class, idFamiliaIngredient); 
        } finally { 
            sesion.close(); 
        }  
        return fam; 
    }  

    public List<FamiliaIngredient> getListFamiliaIngredient() throws HibernateException { 
        List<FamiliaIngredient> listFamiliaIngredient = null;  
        try { 
            init(); 
            listFamiliaIngredient = sesion.createQuery("from familiaIngredient").list(); 
        } finally { 
            sesion.close(); 
        }  
        return listFamiliaIngredient; 
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
