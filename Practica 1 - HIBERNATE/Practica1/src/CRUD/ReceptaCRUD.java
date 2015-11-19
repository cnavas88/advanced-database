/*
 * ReceptaCRUD.java
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.Recepta;
import practica1.ConnectorHB;

/**
 * Esta Clase define los objetos de tipo ReceptaCRUD para comunicarse con la bases de datos
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class ReceptaCRUD {
    // Atributos
    private Session sesion; 
    private Transaction tx;  
    
    /** 
     * Funcion que inserta una receta en la bases de datos
     * @param recepta La receta a guardar
     * @return identificador del receta insertado
     * @exception  HibernateException Error al acceder a los datos
     */
    public int saveRecepta(Recepta recepta) throws HibernateException { 
        int id = 0;  
        try { 
            id = (int) sesion.save(recepta); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        }  
        return id; 
    }  
    
    /** 
     * Funcion que Actualiza una receta en la bases de datos
     * @param recepta La receta a actualizar (Le pasamos la receta entera, no solo el parametro a actualizar)
     * @exception  HibernateException Error al acceder a los datos
     */
    public void updateRecepta(Recepta recepta) throws HibernateException { 
        try{  
            sesion.update(recepta); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } 
    }  
    
    /** 
     * Funcion que borra una receta en la bases de datos
     * @param rec El identificador del receta que vamos a eliminar
     * @exception  HibernateException Error al acceder a los datos
     * @exception  Exception Error al apuntar al elemento
     */ 
    public void deleteRecepta(int rec) throws HibernateException, Exception {
        try {
            Query q = sesion.createQuery("from Recepta where id_recepta = :id ");
            q.setParameter("id", rec);
            Recepta recepta = getRecepta(rec);
            sesion.delete(recepta);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
    }
    
    /** 
     * Funcion que busca 1 receta en la bases de datos
     * @param idRecepta El identificador del receta que vamos a buscar
     * @return devuelve una receta
     * @exception  HibernateException Error al acceder a los datos
     */  
    public Recepta getRecepta(int idRecepta) throws HibernateException { 
        Recepta recepta = null;  
            recepta = (Recepta) sesion.get(Recepta.class, idRecepta); 
        return recepta; 
    }  
    
    /** 
     * Funcion que devuelve una lista con todos los recetas de la bases de datos
     * @return devuelve una lista de recetas
     * @exception  HibernateException Error al acceder a los datos
     */  
    public List<Recepta> getListRecepta() throws HibernateException { 
        List<Recepta> listRecepta = null;  
        listRecepta = sesion.createQuery("from Recepta").list(); 
        return listRecepta; 
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
