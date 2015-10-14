/*
 * Plat.java
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.ConnectorHB;
import practica1.Plat;

/**
 * Esta Clase define los objetos de tipo PlatCRUD para comunicarse con la bases de datos
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class PlatCRUD {
    // Atributos
    private Session sesion; 
    private Transaction tx;  
    
    /** 
     * Funcion que inserta un plato en la bases de datos
     * @param plat El plato a guardar
     * @return identificador del plato insertado
     * @exception  HibernateException Error al acceder a los datos
     */
    public int savePlat(Plat plat) throws HibernateException { 
        int id = 0;  
        try { 
            id = (int) sesion.save(plat); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        }  
        return id; 
    }  
    
    /** 
     * Funcion que Actualiza un plato en la bases de datos
     * @param plat El plato a actualizar (Le pasamos el plato entero, no solo el parametro a actualizar)
     * @exception  HibernateException Error al acceder a los datos
     */
    public void updatePlat(Plat plat) throws HibernateException { 
        try{ 
            sesion.update(plat); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } 
    } 
    
    /** 
     * Funcion que borra un plato en la bases de datos
     * @param plt El identificador del plato que vamos a eliminar
     * @exception HibernateException Error al acceder a los datos
     * @exception Exception Error al apuntar al elemento
     */  
    public void deletePlat(int plt) throws HibernateException, Exception {
        try {            
            Query q = sesion.createQuery("from Plat where id_plat = :id ");
            q.setParameter("id", plt);
            Plat plat = (Plat)q.list().get(0);
            sesion.delete(plat);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
    }  
    
    /** 
     * Funcion que busca 1 plato en la bases de datos
     * @param idPlat El identificador del plato que vamos a buscar
     * @return devuelve un plato
     * @exception  HibernateException Error al acceder a los datos
     */  
    public Plat getPlat(int idPlat) throws HibernateException { 
        Plat plat = null;   
        plat = (Plat) sesion.get(Plat.class, idPlat); 
        return plat; 
    }  
    
    /** 
     * Funcion que devuelve una lista con todos los platos de la bases de datos
     * @return devuelve una lista de plato
     * @exception  HibernateException Error al acceder a los datos
     */  
    public List<Plat> getListPlat() throws HibernateException { 
        List<Plat> listPlat = null;   
        listPlat = sesion.createQuery("from Plat").list(); 
        return listPlat; 
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
