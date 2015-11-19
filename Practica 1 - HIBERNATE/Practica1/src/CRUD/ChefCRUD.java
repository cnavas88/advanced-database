package CRUD;

/*
 * ChefCRUD.java
 */
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica1.Chef;
import practica1.ConnectorHB;
import practica1.Recepta;

/**
 * Esta Clase define los objetos de tipo ChefCRUD para comunicarse con la bases
 * de datos
 *
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class ChefCRUD {

    // Atributos
    private Session sesion;
    private Transaction tx;

    /**
     * Funcion que inserta un chef en la bases de datos
     *
     * @param chef El chef a guardar
     * @return identificador del chef insertado
     * @exception HibernateException Error al acceder a los datos
     */
    public int saveChef(Chef chef) throws HibernateException {
        int id = 0;
        try {
            id = (int) sesion.save(chef);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
        return id;
    }

    /**
     * Funcion que Actualiza un chef en la bases de datos
     * 
     * @param chef El chef a actualizar (Le pasamos el chef entero, no solo el
     * parametro a actualizar)
     * @exception HibernateException Error al acceder a los datos
     */
    public void updateChef(Chef chef) throws HibernateException {
        try {
            sesion.update(chef);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
    }

    /**
     * Funcion que borra un chef en la bases de datos y las recetas asociadas
     * a dicho chef.
     * 
     * @param idChef El identificador del chef que vamos a eliminar
     * @exception HibernateException Error al acceder a los datos
     * @exception Exception Error al apuntar al elemento
     */
    public void deleteChef(int idChef) throws HibernateException, Exception {
        try {
            //deleteReceptaChef(idChef);
            Query q = sesion.createQuery("from Chef where id_chef = :id ");
            q.setParameter("id", idChef);
            Chef chef = getChef(idChef);
            sesion.delete(chef);
            tx.commit();

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
    }

    /**
     * Funcion que busca 1 chef en la bases de datos
     *
     * @param idChef El identificador del chef que vamos a buscar
     * @return devuelve un chef
     * @exception HibernateException Error al acceder a los datos
     */
    public Chef getChef(int idChef) throws HibernateException {
        Chef chef = null;
        String queryString = "from Chef where id_chef = :id";
        Query query = sesion.createQuery(queryString);
        query.setInteger("id", idChef);
        chef = (Chef) query.uniqueResult();
        return chef;
    }

    /**
     * Funcion que busca las recetas asociadas a un chef, de la base de datos.
     *
     * @param idChef El identificador del chef que vamos a buscar
     * @return devuelve una lista de recetas
     * @exception HibernateException Error al acceder a los datos
     */
    public List<Recepta> getListRecepta(int idChef) throws HibernateException {
        List<Recepta> recepta = null;
        String queryString = "from Recepta where id_chef = :id";
        Query query = sesion.createQuery(queryString);
        query.setInteger("id", idChef);
        recepta = query.list();
        return recepta;
    }

    /**
     * Funcion que devuelve una lista con todos los chefs de la bases de datos
     *
     * @return devuelve una lista de chef
     * @exception HibernateException Error al acceder a los datos
     */
    public List<Chef> getListChef() throws HibernateException {
        List<Chef> listChef = null;
        listChef = sesion.createQuery("from Chef").list();
        return listChef;
    }

    /**
     * Funcion que inicia la conexion con hibernate
     * 
     * @exception HibernateException Error al acceder a los datos
     */
    public void init() throws HibernateException {
        sesion = ConnectorHB.getSession();
        tx = sesion.beginTransaction();
    }

    /**
     * Funcion que cierra la conexion con la bases de datos
     * 
     * @exception HibernateException Error al acceder a los datos
     */
    public void close() {
        sesion.close();
    }

    /**
     * Funcion que maneja la excepcion de hibernate
     */
    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrio un error al intentar accceder a los datos", he);
    }
    
    
    
    /* 
     **
     * Funcion que elimina las recetas asociadas a un chef
     *
     * @param idChef el identificador del chef.
     **
     private void deleteReceptaChef(int idChef) {
     try {
     Query q = sesion.createQuery("from Recepta where id_chef = :id ");
     q.setParameter("id", idChef);
     for (Recepta recepta : getListRecepta(idChef)) {
     System.out.println(recepta.getId() + " antes del " + recepta.getChef().getNom());
     sesion.delete(recepta);
     System.out.println(recepta.getId() + " despues del " + recepta.getChef().getNom());
     }
     tx.commit();
     } catch (HibernateException he) {
     manejaExcepcion(he);
     throw he;
     }
     }*/
}
