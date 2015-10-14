/*
 * TipusDeMenjar.java
 */
package practica1;

import java.util.Set;

/**
 * Esta Clase define los objetos de tipo de comida
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class TipusDeMenjar  implements java.io.Serializable{
    // Atributos
    private int id;
    private String nom;
    private String descripcio;
    private Set<Recepta> receptes;
    
   // Constructor vacio
    public TipusDeMenjar(){  }
    
    /** 
     * Constructor del Tipo de comida con parametros 
     * @param nom El nombre del tipo de comida
     * @param descripcio Es la descripcion del tipo de comida
     */
    public TipusDeMenjar( String nom, String descripcio ){
        this.nom = nom;
        this.descripcio = descripcio;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un nombre al tipo de comida
     * @param nom Es un String con el nombre del tipo de comida
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la variable nombre
     * @return El nombre del tipo de comida
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar una descripcion
     * @param descripcio Es un String con la descripcion del tipo de comida
     */
    public void setDescripcio(String descripcio) {
       this.descripcio = descripcio;
    }
    
    /**
     * Metodo get de la variable descripcio
     * @return La descripcion del tipo de comida
     */    
    public String getDescripcio() {
       return this.descripcio;
    }
    
    /** 
     * Metodo Set para asignar un identificador al tipo de comida
     * @param id Es un entero con el identificador del tipo de comida
     */
    public void setId(int id) {
       this.id = id;
    }
    
    /**
     * Metodo get del identificador
     * @return identificador del tipo de comida
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar una lista de recetas
     * @param recepta Es una lista con las recetas que contienen este tipo de comida.
     */
    public void setReceptes(Set<Recepta> recepta) {
       this.receptes = recepta;
    }
    
    /**
     * Metodo get de la variable recepta
     * @return receptas que contienen este tipo de comida
     */
    public Set<Recepta> getReceptes() {
       return this.receptes;
    }
    
    /* Class functions */ 
    /**
     * Sobre escribimos el metodo toString para que nos devuelva el String que nosotros queramos
     * @return La familia de ingredientes printada
     */
    @Override
    public String toString () {
            return "Id Tipus de menjar : "+this.getId() + ", nom: "+this.getNom()+", descripcio: " + this.getDescripcio();
    } 
}
