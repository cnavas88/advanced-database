/*
 * Chef.java
 */

package practica1;
import java.util.Set;

/**
 * Esta Clase define los objetos de tipo Chef.
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Chef  implements java.io.Serializable{
    // Attributes 
    private int id;
    private String nom;
    private int estrelles;
    private Set<Recepta> receptes;
    
    // Constructor vacio
    public Chef(){  }
    
    /** 
     * Constructor de chef con parametros 
     * @param nom El nombre del chef
     * @param estrelles Es el numero de estrellas que tiene un chef
     */
    public Chef( String nom, int estrelles ){
        this.nom = nom;
        this.estrelles = estrelles;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un nombre a la clase Chef
     * @param nom Es un String con el nombre del chef
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la variable nombre
     * @return El nombre del chef
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar la cantidad de estrellas que tiene un chef
     * @param estrelles Es un entero con el numero de estrellas
     */
    public void setEstrelles(int estrelles) {
       this.estrelles = estrelles;
    }
    
    /**
     * Metodo get de la variable estrellas
     * @return la cantidad de estrellas que tiene un chef
     */
    public int getEstrelles() {
       return this.estrelles;
    }
    
    /** 
     * Metodo Set para asignar un identificador al chef
     * @param id Es un entero con el identificador del chef
     */
    public void setId(int id) {
       this.id = id;
    }
     
    /**
     * Metodo get del identificador
     * @return identificador de chef
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar una lista de recetas al chef
     * @param recepta la lista de recetas que tendra el chef
     */
    public void setReceptes(Set<Recepta> recepta) {
       this.receptes = recepta;
    }
    
    /**
     * Metodo get de las recetas
     * @return Las recetas que tiene un chef
     */
    public Set<Recepta> getReceptes() {
       return this.receptes;
    }
    
    /* Class functions */ 
    /**
     * Sobre escribimos el metodo toString para que nos devuelva el String que nosotros queramos
     * @return El Chef printado
     */
    @Override
    public String toString () {
            return "Id Chef : "+this.id+", Nom: "+this.nom+",Estrellas: "+this.estrelles;
    }
    
}
