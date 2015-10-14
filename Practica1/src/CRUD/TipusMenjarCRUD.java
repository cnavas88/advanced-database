/*
 * TipusMenjarCRUD.java
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.ConnectorHB;
import practica1.TipusDeMenjar;

/**
 * Esta Clase define los objetos de tipo TipusMenjarCRUD para comunicarse con la bases de datos
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class TipusMenjarCRUD {
    // Atributos
    private Session sesion; 
    private Transaction tx;  

    /** 
     * Funcion que inserta un tipo de comida en la bases de datos
     * @param tipus El tipo de comida a guardar
     * @return identificador del tipo de comida insertado
     * @exception  HibernateException Error al acceder a los datos
     */    
    public int saveTipusDeMenjar(TipusDeMenjar tipus) throws HibernateException { 
        int id = 0;  
        try {  
            id = (int) sesion.save(tipus); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } 
        return id; 
    }  
    
    /** 
     * Funcion que Actualiza un tipo de comida en la bases de datos
     * @param tipus El tipo de comida a actualizar (Le pasamos el tipo de comida entero, no solo el parametro a actualizar)
     * @exception  HibernateException Error al acceder a los datos
     */
    public void updateTipusDeMenjar(TipusDeMenjar tipus) throws HibernateException { 
        try{ 
            sesion.update(tipus); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } 
    }  
    
    /** 
     * Funcion que borra un tipo de comida en la bases de datos
     * @param tipus El identificador del tipo de comida que vamos a eliminar
     * @exception  HibernateException Error al acceder a los datos
     * @exception  Exception Error al apuntar a los datos
     */ 
    public void deleteTipusDeMenjar(int tipus) throws HibernateException, Exception {
        try {
            Query q = sesion.createQuery("from TipusDeMenjar where id_tipusDeMenjar = :id ");
            q.setParameter("id", tipus);
            TipusDeMenjar tipusDeMenjar = getTipusDeMenjar(tipus);
            sesion.delete(tipusDeMenjar);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
    }
    
    /** 
     * Funcion que busca 1 tipo de comida en la bases de datos
     * @param idTipusDeMenjar El identificador del tipo de comida que vamos a buscar
     * @return devuelve un tipo de comida
     * @exception  HibernateException Error al acceder a los datos
     */ 
    public TipusDeMenjar getTipusDeMenjar(int idTipusDeMenjar) throws HibernateException { 
        TipusDeMenjar tipus;  
        tipus = (TipusDeMenjar) sesion.get(TipusDeMenjar.class, idTipusDeMenjar); 
        return tipus; 
    }  
    
    /** 
     * Funcion que devuelve una lista con todos los tipos de comida de la bases de datos
     * @return devuelve una lista de tipo de comida
     * @exception  HibernateException Error al acceder a los datos
     */ 
    public List<TipusDeMenjar> getListTipusDeMenjar() throws HibernateException { 
        List<TipusDeMenjar> listTipusDeMenjar = null;  
        listTipusDeMenjar = sesion.createQuery("from TipusDeMenjar").list(); 
        return listTipusDeMenjar; 
    }
    
    /** 
     * Funcion que inicia la conexion con hibernate
     * @exception  HibernateException Error al acceder a los datos
     */ 
    public void init() throws HibernateException { 
        sesion = ConnectorHB.getSession();
        tx = sesion.beginTransaction(); 
    }
    
    /** 
     * Funcion que cierra la conexion con la bases de datos
     * @exception  HibernateException Error al acceder a los datos
     */     
    public void close(){
        sesion.close();
    } 
    
    /** 
     * Funcion que maneja la excepcion de hibernate
     */  
    private void manejaExcepcion(HibernateException he) throws HibernateException { 
        tx.rollback(); 
        throw new HibernateException("Ocurrio un error al intentar accceder a los datos", he); 
    }     
}
