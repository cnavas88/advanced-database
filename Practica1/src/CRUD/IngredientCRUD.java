/*
 * IngredientCRUD.java
 */
package CRUD;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.ConnectorHB;
import practica1.FamiliaIngredient;
import practica1.Ingredient;

/**
 * Esta Clase define los objetos de tipo IngredientCRUD para comunicarse con la bases de datos
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class IngredientCRUD {
    // Atributos
    private Session sesion; 
    private Transaction tx;  

    /** 
     * Funcion que inserta un ingrediente en la bases de datos
     * @param ing El ingrediente a guardar
     * @return identificador del ingrediente insertado
     * @exception  HibernateException Error al acceder a los datos
     */    
    public int saveIngredient(Ingredient ing) throws HibernateException { 
        int id = 0;  
        try { 
            id = (int) sesion.save(ing); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        }  
        return id; 
    }  
    
    /** 
     * Funcion que Actualiza un ingrediente en la bases de datos
     * @param ing El ingrediente a actualizar (Le pasamos el ingrediente entero, no solo el parametro a actualizar)
     * @exception  HibernateException Error al acceder a los datos
     */
    public void updateIngredient(Ingredient ing) throws HibernateException { 
        try{ 
            sesion.update(ing); 
            tx.commit(); 
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        }
    }  
    
    /** 
     * Funcion que borra un ingrediente en la bases de datos
     * @param ing El identificador del ingrediente que vamos a eliminar
     * @exception  HibernateException Error al acceder a los datos
     * @exception  Exception Error al apuntar al elemento
     */  
    public void deleteIngredient(int ing) throws HibernateException, Exception { 
        try {  
            Query q = sesion.createQuery("from Ingredient where id_ingredient = :id ");
            q.setParameter("id", ing);
            Ingredient ingredient = getIngredient(ing);
            sesion.delete(ingredient);
            tx.commit();
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        }
    }  

    /** 
     * Funcion que busca 1 ingrediente en la bases de datos
     * @param idIngredient El identificador del ingrediente que vamos a buscar
     * @return devuelve un ingrediente
     * @exception HibernateException Error al acceder a los datos
     */     
    public Ingredient getIngredient(int idIngredient) throws HibernateException { 
        Ingredient ing;   
        ing = (Ingredient) sesion.get(Ingredient.class, idIngredient);  
        return ing; 
    }  
    
    /** 
     * Funcion que devuelve una lista con todos los ingredientes de la bases de datos
     * @return devuelve una lista de ingrediente
     * @exception HibernateException Error al acceder a los datos
     */  
    public List<Ingredient> getListIngredient() throws HibernateException { 
        List<Ingredient> listIngredient;  
        listIngredient = sesion.createQuery("from Ingredient").list();   
        return listIngredient; 
    }  
    
    /** 
     * Funcion que busca una familia de ingredientes de la bases de datos
     * Esta funcion esta aqui porque solo necesitamos acceder a la tabla familia de ingredientes, cuando estemos en ingrediente
     * @param idFam El identificador de la familia de ingrediente que vamos a buscar
     * @return devuelve una familia de ingrediente
     * @exception  HibernateException Error al acceder a los datos
     */   
    public FamiliaIngredient getFamiliaIngredient(int idFam) throws HibernateException { 
        FamiliaIngredient familia;  
        familia = (FamiliaIngredient) sesion.get(FamiliaIngredient.class, idFam);  
        return familia; 
    }  
    
    /** 
     * Funcion que devuelve una lista con todas las familias de ingredientes de la bases de datos
     * @return devuelve una lista de las familias de ingrediente
     * @exception  HibernateException Error al acceder a los datos
     */ 
    public List<FamiliaIngredient> getListFamiliaIngredient() throws HibernateException { 
        List<FamiliaIngredient> listFamIngredient;  
        listFamIngredient = sesion.createQuery("from FamiliaIngredient").list(); 
        return listFamIngredient; 
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
